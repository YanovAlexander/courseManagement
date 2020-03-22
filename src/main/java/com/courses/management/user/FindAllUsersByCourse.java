package com.courses.management.user;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.Commands;
import com.courses.management.common.commands.util.InputString;

import java.util.List;

public class FindAllUsersByCourse implements Command {
    private final View view;
    private final UserDAO dao;
    private static final int COURSE_TITLE_INDEX = 1;

    public FindAllUsersByCourse(View view, UserDAO dao) {
        this.view = view;
        this.dao = dao;
    }

    @Override
    public String command() {
        return Commands.FIND_ALL_USERS_BY_COURSE;
    }

    @Override
    public void process(InputString input) {
        final String[] parameters = input.getParameters();
        String courseTitle = parameters[COURSE_TITLE_INDEX];
        List<User> users = dao.getUsersByCourse(courseTitle);
        if (users.isEmpty()) {
            view.write("There is no users by specified course title");
        } else {
            users.forEach(user -> Users.printUser(view, user));
        }
    }
}
