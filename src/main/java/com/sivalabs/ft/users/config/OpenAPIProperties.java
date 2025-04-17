package com.sivalabs.ft.users.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "ft.openapi")
@Validated
public record OpenAPIProperties(
        @DefaultValue("UserService API") String title,
        @DefaultValue("UserService API Swagger Documentation") String description,
        @DefaultValue("v1.0.0") String version,
        Contact contact) {

    public record Contact(@DefaultValue("SivaLabs") String name, @DefaultValue("support@sivalabs.in") String email) {}
}
