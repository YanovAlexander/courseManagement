package com.courses.management.course;

import com.courses.management.common.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseDAO extends DataAccessObject<Course> {
    private final static String INSERT = "INSERT INTO course(title, status) " +
            "VALUES(?, ?);";

    public CourseDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Course course) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, course.getTitle());
            statement.setString(2, course.getCourseStatus().getStatus());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
