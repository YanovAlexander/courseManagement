package com.courses.management.course;

import com.courses.management.common.exceptions.ErrorMessage;
import com.courses.management.config.DatabaseConnector;
import com.courses.management.user.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/course/*")
public class CourseServlet extends HttpServlet {
    private Courses service;

    @Override
    public void init() throws ServletException {
        super.init();
        service = new Courses(new CourseDAOImpl(DatabaseConnector.getDataSource()), new UserDAOImpl(DatabaseConnector.getDataSource()));
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
            ErrorMessage errorMessage = CourseValidator.validateCreateCourse(req);
            if (!errorMessage.getErrors().isEmpty()) {
                req.setAttribute("errorMessage", errorMessage);
                req.setAttribute("courseStatuses", CourseStatus.values());
                req.getRequestDispatcher("/view/create_course.jsp").forward(req, resp);
            }
            final Course course = service.createCourse(req);
            req.setAttribute("course_title", course.getTitle());
            req.getRequestDispatcher("/view/course_created.jsp").forward(req, resp);
        }
    }

    private String getAction(HttpServletRequest req) {
        final String requestURI = req.getRequestURI();
        String requestPathWithServletContext = req.getContextPath() + req.getServletPath();
        return requestURI.substring(requestPathWithServletContext.length());
    }
}
