package com.sivalabs.ft.users;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ft")
public record ApplicationProperties(
        @NotBlank String keycloakUrl,
        @NotBlank String realmName,
        @NotBlank String masterRealmUserName,
        @NotBlank String masterRealmUserPassword) {}
