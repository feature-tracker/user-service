package com.sivalabs.ft.users.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.sivalabs.ft.users.AbstractIT;
import com.sivalabs.ft.users.domain.User;
import com.sivalabs.ft.users.domain.UserService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

class UserControllerTests extends AbstractIT {

    @MockitoBean
    UserService userService;

    @Test
    void shouldGetAllUsers() {
        when(userService.findAllUsers())
                .thenReturn(List.of(
                        new User("id1", "siva", "siva@gmail.com", "Siva Katamreddy", "ROLE_USER"),
                        new User("id2", "rambo", "rambo@gmail.com", "John Rambo", "ROLE_USER")));

        var result = mvc.get().uri("/api/users");
        assertThat(result)
                .hasStatusOk()
                .bodyJson()
                .extractingPath("$.size()")
                .asNumber()
                .isEqualTo(2);
    }
}
