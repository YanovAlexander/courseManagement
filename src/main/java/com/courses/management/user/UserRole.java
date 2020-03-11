package com.courses.management.user;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {
    ADMIN("ADMIN"),
    STUDENT("STUDENT"),
    NEWCOMER("NEWCOMER");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static Optional<UserRole> getUserRole(String status) {
        return Arrays.stream(UserRole.values())
                .filter(enumValue -> enumValue.getRole().equals(status))
                .findAny();
    }
}
