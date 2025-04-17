package com.sivalabs.ft.users;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ft")
public record ApplicationProperties() {}
