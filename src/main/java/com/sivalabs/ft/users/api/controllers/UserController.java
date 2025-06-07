package com.sivalabs.ft.users.api.controllers;

import com.sivalabs.ft.users.api.dtos.UserDto;
import com.sivalabs.ft.users.domain.KeycloakService;
import com.sivalabs.ft.users.domain.SyncUserCommand;
import com.sivalabs.ft.users.domain.User;
import com.sivalabs.ft.users.domain.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users API")
class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final KeycloakService keycloakService;

    UserController(UserService userService, KeycloakService keycloakService) {
        this.userService = userService;
        this.keycloakService = keycloakService;
    }

    @GetMapping("")
    @Operation(
            summary = "Find all users",
            description = "Find all users",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successful response",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        array = @ArraySchema(schema = @Schema(implementation = UserDto.class))))
            })
    List<UserDto> getUsers() {
        return keycloakService.findAllUsers().stream().map(this::toUserDto).toList();
    }

    private UserDto toUserDto(User user) {
        return new UserDto(user.getUuid(), user.getEmail(), user.getFullName(), user.getRole());
    }

    @PutMapping("/{uuid}")
    @Operation(
            summary = "Sync user info",
            description = "Create or Update an existing user info",
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful response"),
                @ApiResponse(responseCode = "400", description = "Invalid request"),
                @ApiResponse(responseCode = "401", description = "Unauthorized"),
                @ApiResponse(responseCode = "403", description = "Forbidden"),
            })
    void syncUser(@PathVariable String uuid, @RequestBody SyncUserPayload payload) {
        var cmd = new SyncUserCommand(uuid, payload.email(), payload.fullName(), payload.role());
        userService.createOrUpdateUser(cmd);
    }

    record SyncUserPayload(
            @NotEmpty(message = "Email is required") String email,
            @NotEmpty(message = "Full name is required") String fullName,
            @NotEmpty(message = "Role is required") String role) {}
}
