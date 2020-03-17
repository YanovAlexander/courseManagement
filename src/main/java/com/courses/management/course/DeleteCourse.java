package com.courses.management.course;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.InputString;

public class DeleteCourse implements Command {
    private View view;
    private CourseDAO courseDAO;

    public DeleteCourse(View view) {
        this.view = view;
        this.courseDAO = new CourseDAOImpl();
    }

    @Override
    public String command() {
        return "delete_course|title";
    }

    @Override
    public void process(InputString input) {
        String title = input.getParameters()[1];
        Course course = courseDAO.get(title);
        if (course == null || course.getTitle() == null) {
            throw new IllegalArgumentException(String.format("Course  with title %s not exists", title));
        }
        course.setCourseStatus(CourseStatus.DELETED);
        courseDAO.update(course);
        view.write("Course moved to deleted");
    }
}
