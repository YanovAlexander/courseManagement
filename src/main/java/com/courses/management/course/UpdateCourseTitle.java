package com.courses.management.course;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.InputString;

public class UpdateCourseTitle implements Command {
    private View view;
    private CourseDAO courseDAO;

    public UpdateCourseTitle(View view) {
        this.view = view;
        this.courseDAO = new CourseDAOImpl();
    }

    @Override
    public String command() {
        return "update_course_title|oldTitle|newTitle";
    }

    @Override
    public void process(InputString input) {
        input.validateParameters(command());
        String oldTitle = input.getParameters()[1];
        Course course = courseDAO.get(oldTitle);
        if (course == null || course.getTitle() == null) {
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
        if (course != null) {
            throw new IllegalArgumentException(String.format("Course with title %s already exists", title));
        }
    }
}
