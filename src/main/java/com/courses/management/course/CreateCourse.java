package com.courses.management.course;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.InputString;

public class CreateCourse implements Command {
    private final View view;
    private CourseDAO courseDAO;

    public CreateCourse(View view) {
        this.view = view;
        courseDAO = new CourseDAOImpl();
    }

    @Override
    public String command() {
        return "create_course|title";
    }

    @Override
    public void process(InputString input) {
        Course course = Courses.mapCourse(input);
        validateTitle(course.getTitle());
        courseDAO.create(course);
        view.write(String.format("Course created with title - %s", course.getTitle()));
    }

    private void validateTitle(String title) {
        Course course = courseDAO.get(title);
        if (course != null) {
            throw new IllegalArgumentException(String.format("Course with title %s already exists", title));
        }
    }
}
