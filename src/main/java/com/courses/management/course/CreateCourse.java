package com.courses.management.course;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.Commands;
import com.courses.management.common.commands.util.InputString;

public class CreateCourse implements Command {
    private final View view;
    private CourseDAO courseDAO;

    public CreateCourse(View view, CourseDAO dao) {
        this.view = view;
        courseDAO = dao;
    }

    @Override
    public String command() {
        return Commands.CREATE_COURSE;
    }

    @Override
    public void process(InputString input) {
        Course course = Courses.mapCourse(input);
        validateTitle(course.getTitle());
        courseDAO.create(course);
        view.write(String.format("Course created with title - %s", course.getTitle()));
    }

    private void validateTitle(String title) {
        if (title.trim().isEmpty()) {
            throw new IllegalArgumentException("Course title can't be empty");
        }

        Course course = courseDAO.get(title);
        if (course != null) {
            throw new IllegalArgumentException(String.format("Course with title %s already exists", title));
        }
    }
}
