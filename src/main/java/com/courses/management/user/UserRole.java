package com.courses.management.user;

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
}
