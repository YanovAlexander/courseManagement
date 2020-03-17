package com.courses.management.course;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.Commands;
import com.courses.management.common.commands.util.InputString;

import java.util.Objects;

public class UpdateCourseTitle implements Command {
    private View view;
    private CourseDAO courseDAO;

    public UpdateCourseTitle(View view) {
        this.view = view;
        this.courseDAO = new CourseDAOImpl();
    }

    @Override
    public String command() {
        return Commands.UPDATE_COURSE_TITLE;
    }

    @Override
    public void process(InputString input) {
        String oldTitle = input.getParameters()[1];
        Course course = courseDAO.get(oldTitle);
        if (Objects.isNull(course)) {
            throw new IllegalArgumentException(String.format("Course  with title %s not exists", oldTitle));
        }

        String newTitle = input.getParameters()[2];
        validateTitle(newTitle);
        course.setTitle(newTitle);
        courseDAO.update(course);
        view.write("Course title updated");
    }

    private void validateTitle(String title) {
        Course course = courseDAO.get(title);
        if (Objects.nonNull(course)) {
            throw new IllegalArgumentException(String.format("Course with title %s already exists", title));
        }
    }
}
