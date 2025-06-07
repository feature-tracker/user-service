package com.sivalabs.ft.users.config;

import com.sivalabs.ft.users.ApplicationProperties;
import org.keycloak.admin.client.Keycloak;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class KeycloakConfig {
    private final ApplicationProperties properties;

    KeycloakConfig(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Bean
    Keycloak keycloak() {
        return Keycloak.getInstance(
                properties.keycloakUrl(),
                "master",
                properties.masterRealmUserName(),
                properties.masterRealmUserPassword(),
                "admin-cli");
    }
}
