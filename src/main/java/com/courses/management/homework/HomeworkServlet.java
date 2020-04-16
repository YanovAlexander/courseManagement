package com.courses.management.homework;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@WebServlet(urlPatterns = "/homework/*")
@Configurable
public class HomeworkServlet extends HttpServlet {
    private Homeworks homeworks;

    public HomeworkServlet() {
    }

    @Autowired
    public void setHomeworks(Homeworks homeworks) {
        this.homeworks = homeworks;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        if (action.startsWith("/upload")) {
            String courseId = req.getParameter("course_id");
            req.setAttribute("courseId", courseId);
            req.getRequestDispatcher("/view/create_homework.jsp").forward(req, resp);
        } else if (action.startsWith("/get")) {
            getHomework(req, resp);
        } else if (action.startsWith("/preview")) {
            final String homeworkId = req.getParameter("id");
            req.setAttribute("homeworkId", homeworkId);
            req.getRequestDispatcher("/view/preview_homework.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);

        if (action.startsWith("/upload")) {
            Integer courseId = null;
            if (ServletFileUpload.isMultipartContent(request)) {
                try {
                    courseId = Integer.valueOf(request.getParameter("course_id"));
                    List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                    homeworks.uploadFile(multiparts, courseId);
                } catch (Exception ex) {
                    processError(request, response, "File Upload Failed due to " + ex.getMessage(),
                            "/view/create_homework.jsp");
                }
            } else {
                processError(request, response, "No File found", "/view/create_homework.jsp");
            }
            response.sendRedirect(String.format("/course/get?id=%s", courseId));
        }
    }

    private void processError(HttpServletRequest request, HttpServletResponse response, String message, String jspPath)
            throws ServletException, IOException {
        request.setAttribute("error", message);
        request.getRequestDispatcher(jspPath).forward(request, response);
    }

    private String getAction(HttpServletRequest req) {
        final String requestURI = req.getRequestURI();
        String requestPathWithServletContext = req.getContextPath() + req.getServletPath();
        return requestURI.substring(requestPathWithServletContext.length());
    }

    private void getHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer homeworkId = Integer.valueOf(req.getParameter("id"));
        Homework homework = homeworks.getHomework(homeworkId);
        File file = new File(homework.getPath());
        if (!file.exists()) {
            processError(req, resp, "No File found", "/view/course_details.jsp");
        }

        resp.setHeader("Content-Type", getServletContext().getMimeType(file.getName()));
        resp.setHeader("Content-Length", String.valueOf(file.length()));
        resp.setHeader("Content-Disposition", String.format("inline; filename=\"%s\"", homework.getTitle()));
        Files.copy(file.toPath(), resp.getOutputStream());
    }
}
