package com.courses.management.course;

import com.courses.management.common.DataAccessObject;
import com.courses.management.common.DatabaseConnector;
import com.courses.management.common.View;

public class CourseService {
    private final View view;
    private DataAccessObject<Course> courseDAO;


    public CourseService(View view) {
        this.view = view;
        DatabaseConnector connector = new DatabaseConnector();
        courseDAO = new CourseDAO(connector.getConnection());
    }

    public void createCourse() {
        view.write("Enter a course title");
        String title = validate(view.read());
        Course course = new Course();
        course.setTitle(title);
        course.setCourseStatus(CourseStatus.NOT_STARTED);
        courseDAO.create(course);
    }


    private String validate(String value) {
        while (value.trim().isEmpty()) {
            view.write("Please enter the correct title");
            value = view.read();
        }
        return value;
    }
}
