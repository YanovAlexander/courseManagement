package com.courses.management.homework;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class HomeworkServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        if (action.startsWith("/upload")) {
            req.getRequestDispatcher("/view/create_homework.jsp").forward(req, resp);
        } else if (action.startsWith("/get")) {
//            Homeworks.getHomework();
            File file = new File("C:\\Users\\Oleksandr_Yanov1\\Desktop\\GoIT\\ProjectCritiries.doc");
            file.exists();
            resp.setHeader("Content-Type", getServletContext().getMimeType(file.getName()));
            resp.setHeader("Content-Length", String.valueOf(file.length()));
            resp.setHeader("Content-Disposition", "inline; filename=\"ProjectCritiries.doc\"");
            Files.copy(file.toPath(), resp.getOutputStream());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);

        if (action.startsWith("/upload")) {
            if (ServletFileUpload.isMultipartContent(request)) {
                try {
                    List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                    request.setAttribute("message", "File Uploaded Successfully");
                } catch (Exception ex) {
                    request.setAttribute("message", "File Upload Failed due to " + ex);
                }
            } else {
                request.setAttribute("message", "No File found");
            }
            request.getRequestDispatcher("/homework_uploaded.jsp").forward(request, response);
        }
    }

    private String getAction(HttpServletRequest req) {
        final String requestURI = req.getRequestURI();
        String requestPathWithServletContext = req.getContextPath() + req.getServletPath();
        return requestURI.substring(requestPathWithServletContext.length());
    }
}
