package com.courses.management.course;

public class CourseAlreadyExistsError extends RuntimeException {
    public CourseAlreadyExistsError(String message) {
        super(message);
    }
}
