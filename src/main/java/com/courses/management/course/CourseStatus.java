package com.courses.management.course;

import java.util.Arrays;
import java.util.Optional;

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

    public static Optional<CourseStatus> getCourseStatus(String status) {
        return Arrays.stream(CourseStatus.values())
                .filter(enumValue -> enumValue.getStatus().equals(status))
                .findAny();
    }
}
