package com.courses.management.course;

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
        } if (action.startsWith("/get")) {
            final String id = req.getParameter("id");
            final Course course = service.getById(Integer.valueOf(id));
            req.setAttribute("course", course);
            req.getRequestDispatcher("/view/course_details.jsp").forward(req, resp);
        }
    }

    private String getAction(HttpServletRequest req) {
        final String requestURI = req.getRequestURI();
        String requestPathWithServletContext = req.getContextPath() + req.getServletPath();
        return requestURI.substring(requestPathWithServletContext.length());
    }
}
