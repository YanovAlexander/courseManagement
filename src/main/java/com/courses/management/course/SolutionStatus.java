package com.courses.management.course;

public enum SolutionStatus {
    COMPLETED("COMPLETED"),
    STARTED("STARTED"),
    NEW("NEW");

    private String status;

    SolutionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
