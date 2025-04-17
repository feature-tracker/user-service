package com.sivalabs.ft.users.domain;

public record SyncUserCommand(String uuid, String email, String fullName, String role) {}
