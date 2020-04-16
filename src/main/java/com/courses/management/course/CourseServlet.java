package com.courses.management.course;

import com.courses.management.common.Validator;
import com.courses.management.common.exceptions.ErrorMessage;
import com.courses.management.config.HibernateDatabaseConnector;
import com.courses.management.user.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = "/course/*")
public class CourseServlet extends HttpServlet {
    private Courses service;

    @Override
    public void init() throws ServletException {
        super.init();
        service = new Courses(new CourseDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);

        if (action.startsWith("/showCourses")) {
            List<Course> courses = service.showCourses();
            req.setAttribute("courses", courses);
            req.getRequestDispatcher("/view/show_courses.jsp").forward(req, resp);
        } else if (action.startsWith("/get")) {
            final String id = req.getParameter("id");
            final Course course = service.getById(Integer.valueOf(id));
            req.setAttribute("course", course);
            req.getRequestDispatcher("/view/course_details.jsp").forward(req, resp);
        } else if (action.startsWith("/createCourse")) {
            req.setAttribute("courseStatuses", CourseStatus.values());
            req.getRequestDispatcher("/view/create_course.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        if (action.startsWith("/createCourse")) {
            Course course = mapCourse(req);
            List<ErrorMessage> errorMessages = validateCourse(course);
            if (!errorMessages.isEmpty()) {
                req.setAttribute("errors", errorMessages);
                req.setAttribute("courseStatuses", CourseStatus.values());
                req.getRequestDispatcher("/view/create_course.jsp").forward(req, resp);
            } else {
                service.createCourse(course);
                req.setAttribute("course_title", course.getTitle());
                req.getRequestDispatcher("/view/course_created.jsp").forward(req, resp);
            }
        }
    }

    private Course mapCourse(HttpServletRequest req) {
        final String courseTitle = req.getParameter("title").trim();
        final String course_status = req.getParameter("course_status").trim();
        final Optional<CourseStatus> courseStatus = CourseStatus.getCourseStatus(course_status);
        Course course = new Course();
        course.setTitle(courseTitle);
        course.setCourseStatus(courseStatus.get());
        return course;
    }

    private List<ErrorMessage> validateCourse(Course course) {
        final List<ErrorMessage> errorMessages = Validator.validateEntity(course);
        final Course persistentCourse = service.getByTitle(course.getTitle());
        if (Objects.nonNull(persistentCourse) && !persistentCourse.getTitle().isEmpty()) {
            errorMessages.add(new ErrorMessage("", "course with title already exists"));
        }
        return errorMessages;
    }


    private String getAction(HttpServletRequest req) {
        final String requestURI = req.getRequestURI();
        String requestPathWithServletContext = req.getContextPath() + req.getServletPath();
        return requestURI.substring(requestPathWithServletContext.length());
    }
}
