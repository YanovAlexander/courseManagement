package com.courses.management.course;

import com.courses.management.common.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Courses {
    private static final Logger LOG = LogManager.getLogger(Courses.class);
    private CourseDAO courseDAO;

    public Courses(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
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
        final Course course = courseDAO.get(id);
        return course;
    }

    public Course getByTitle(String title) {
        return courseDAO.get(title);
    }

    public Course createCourse(Course course) {
        courseDAO.create(course);
        return course;
    }
}
