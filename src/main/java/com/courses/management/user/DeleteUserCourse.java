package com.courses.management.user;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.Commands;
import com.courses.management.common.commands.util.InputString;

import java.util.Objects;

public class DeleteUserCourse implements Command {
    private static final int EMAIL_INDEX = 1;
    private final View view;
    private final UserDAO dao;

    public DeleteUserCourse(View view, UserDAO dao) {
        this.view = view;
        this.dao = dao;
    }

    @Override
    public String command() {
        return Commands.DELETE_USER_COURSE;
    }

    @Override
    public void process(InputString input) {
        String[] parameters = input.getParameters();
        final String email = parameters[EMAIL_INDEX];
        User user = dao.get(email);
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("Can't find user with provided email");
        }

        user.setCourse(null);
        user.setStatus(UserStatus.NOT_ACTIVE);
        dao.removeUserCourseAndSetStatus(email, UserStatus.NOT_ACTIVE);
        view.write(String.format("User course removed and status set to %s", UserStatus.NOT_ACTIVE.getStatus()));
    }
}
