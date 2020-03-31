package com.courses.management.course;

import com.courses.management.common.exceptions.ErrorMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CourseValidator {
    public static ErrorMessage validateCreateCourse(HttpServletRequest request) {
        final String courseTitle = (String) request.getParameter("title");
        final String course_status = (String) request.getParameter("course_status");

        ErrorMessage errorMessage = new ErrorMessage();
        List<String> errors = new ArrayList<>();
        if (Objects.nonNull(courseTitle) && courseTitle.isEmpty()) {
            errors.add("Course Title is empty");
        }
        final Optional<CourseStatus> courseStatus = CourseStatus.getCourseStatus(course_status);
        if (Objects.nonNull(courseStatus) && courseStatus.isEmpty()) {
            errors.add("Course status is incorrect");
        }
        errorMessage.setErrors(errors);
        return errorMessage;
    }
}
