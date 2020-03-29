package com.courses.management.course;

import com.courses.management.common.View;
import com.courses.management.common.commands.util.InputString;

import javax.sql.DataSource;
import java.util.List;

public class Courses {
    private CourseDAO courseDAO;

    public Courses(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public static Course mapCourse(InputString input) {
        String[] parameters = input.getParameters();
        String title = parameters[1];
        Course course = new Course();
        course.setTitle(title);
        course.setCourseStatus(CourseStatus.NOT_STARTED);
        return course;
    }

    public static void printCourse(View view, Course course) {
        view.write("Course:");
        view.write(String.format("\t title = %s", course.getTitle()));
        view.write(String.format("\t status = %s", course.getCourseStatus()));
    }

    public List<Course> showCourses() {
        return courseDAO.getAll();
    }

    public Course getById(Integer id) {
        return courseDAO.get(id);
    }
}
