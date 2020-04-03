package com.courses.management.course;

import com.courses.management.common.View;
import com.courses.management.common.commands.util.InputString;
import com.courses.management.user.User;
import com.courses.management.user.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class Courses {
    private static final Logger LOG = LogManager.getLogger(Courses.class);
    private final UserDAO userDAO;
    private CourseDAO courseDAO;

    public Courses(CourseDAO courseDAO, UserDAO userDAO) {
        this.courseDAO = courseDAO;
        this.userDAO = userDAO;
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
        final Course course = courseDAO.get(id);
        final List<User> users = userDAO.getUsersByCourse(course.getTitle());
        //TODO
//        course.setUsers(users);
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
