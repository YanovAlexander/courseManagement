package com.courses.management.user;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.Commands;
import com.courses.management.common.commands.util.InputString;
import com.courses.management.course.Course;
import com.courses.management.course.CourseDAO;

import java.util.Objects;

public class UpdateUserCourse implements Command {
    private final View view;
    private final UserDAO userDAO;
    private static final int USER_EMAIL_INDEX = 1;
    private static final int COURSE_TITLE_INDEX = 2;
    private final CourseDAO courseDAO;

    public UpdateUserCourse(View view, UserDAO userDAO, CourseDAO courseDAO) {
        this.view = view;
        this.userDAO = userDAO;
        this.courseDAO = courseDAO;
    }

    @Override
    public String command() {
        return Commands.UPDATE_USER_COURSE;
    }

    @Override
    public void process(InputString input) {
        String[] parameters = input.getParameters();
        String userEmail = parameters[USER_EMAIL_INDEX];
        String courseTitle = parameters[COURSE_TITLE_INDEX];
        User user = userDAO.get(userEmail);
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException(String.format("Can't find user by specified email = %s", userEmail));
        }
        final Course course = courseDAO.get(courseTitle);
        if (Objects.isNull(course)) {
            throw new IllegalArgumentException(String.format("Can't find course by specified title = %s", courseTitle));
        }
        user.setCourse(course);
        user.setStatus(UserStatus.ACTIVE);
        userDAO.update(user);
        view.write("User successfully updated");
    }
}
