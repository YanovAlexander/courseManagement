package com.courses.management.user;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.Commands;
import com.courses.management.common.commands.util.InputString;

import java.util.Objects;

public class UpdateUserEmail implements Command {
    private final View view;
    private final UserDAO dao;
    private static final int OLD_EMAIL_INDEX = 1;
    private static final int NEW_EMAIL_INDEX = 2;

    public UpdateUserEmail(View view, UserDAO dao) {
        this.view = view;
        this.dao = dao;
    }

    @Override
    public String command() {
        return Commands.UPDATE_USER_EMAIL;
    }

    @Override
    public void process(InputString input) {
        String[] parameters = input.getParameters();
        String oldEmail = parameters[OLD_EMAIL_INDEX];
        String newEmail = parameters[NEW_EMAIL_INDEX];

        final User user = dao.get(oldEmail);
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("Can't find user by specified email");
        }

        Users.validateEmail(newEmail);
        user.setEmail(newEmail);
        dao.update(user);
        view.write("User updated successfully!");
    }
}
