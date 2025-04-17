package com.sivalabs.ft.users.api.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sivalabs.ft.users.AbstractIT;
import com.sivalabs.ft.users.domain.User;
import com.sivalabs.ft.users.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class UserControllerTests extends AbstractIT {

    @Autowired
    UserRepository userRepository;

    @Test
    void shouldGetAllUsers() {
        var result = mvc.get().uri("/api/users");
        assertThat(result)
                .hasStatusOk()
                .bodyJson()
                .extractingPath("$.size()")
                .asNumber()
                .isEqualTo(2);
    }

    @Test
    void shouldCreateNewUser() {
        var payload =
                """
            {
                "email": "uuid-8888@gmail.com",
                "fullName": "UUID 8888",
                "role": "ROLE_USER"
            }
            """;

        var result = mvc.put()
                .uri("/api/users/{uuid}", "uuid-8888")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload);
        assertThat(result).hasStatus(HttpStatus.OK);

        User user = userRepository.findByUuid("uuid-8888").orElseThrow();
        assertThat(user.getUuid()).isEqualTo("uuid-8888");
        assertThat(user.getEmail()).isEqualTo("uuid-8888@gmail.com");
        assertThat(user.getFullName()).isEqualTo("UUID 8888");
        assertThat(user.getRole()).isEqualTo("ROLE_USER");
    }

    @Test
    void shouldUpdateExistingUser() {
        var payload =
                """
            {
                "email": "uuid-2@yahoomail.com",
                "fullName": "UUID 2121",
                "role": "ROLE_USER"
            }
            """;

        var result = mvc.put()
                .uri("/api/users/{uuid}", "uuid-2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload);
        assertThat(result).hasStatus(HttpStatus.OK);

        User user = userRepository.findByUuid("uuid-2").orElseThrow();
        assertThat(user.getUuid()).isEqualTo("uuid-2");
        assertThat(user.getEmail()).isEqualTo("uuid-2@yahoomail.com");
        assertThat(user.getFullName()).isEqualTo("UUID 2121");
        assertThat(user.getRole()).isEqualTo("ROLE_USER");
    }
}
