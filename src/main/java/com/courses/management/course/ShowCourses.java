package com.courses.management.course;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.InputString;

import java.util.List;

public class ShowCourses implements Command {
    private View view;
    private CourseDAO courseDAO;

    public ShowCourses(View view) {
        this.view = view;
        this.courseDAO = new CourseDAOImpl();
    }

    @Override
    public String command() {
        return "show_courses";
    }

    @Override
    public void process(InputString input) {
        List<Course> courses = courseDAO.getAll();
        courses.forEach(course -> Courses.printCourse(view, course));
    }
}
