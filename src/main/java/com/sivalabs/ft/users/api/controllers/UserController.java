package com.sivalabs.ft.users.api.controllers;

import com.sivalabs.ft.users.api.dtos.UserDto;
import com.sivalabs.ft.users.domain.User;
import com.sivalabs.ft.users.domain.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    UserController(UserService userService) {
        this.userService = userService;
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
        return userService.findAllUsers().stream().map(this::toUserDto).toList();
    }

    private UserDto toUserDto(User user) {
        return new UserDto(user.username(), user.email(), user.fullName(), user.role());
    }
}
