package com.courses.management.course;

public enum CourseStatus {
    NOT_STARTED("NOT_STARTED"),
    IN_PROGRESS("IN_PROGRESS"),
    FINISHED("FINISHED");

    private String status;

    CourseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
