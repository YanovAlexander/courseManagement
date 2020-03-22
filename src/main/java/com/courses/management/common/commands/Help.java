package com.courses.management.common.commands;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.InputString;
import com.courses.management.course.CourseStatus;
import com.courses.management.user.UserRole;
import com.courses.management.user.UserStatus;

import static com.courses.management.common.commands.util.Commands.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Help implements Command {
    private View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public String command() {
        return HELP;
    }

    @Override
    public void process(InputString input) {
        view.write("-----------------------COMMANDS--------------------------");
        view.write("---------------------------------------------------------");
        view.write("\t| " + CREATE_COURSE);
        view.write("\t|\t-> create a course with a title");
        view.write("---------------------------------------------------------");

        view.write("\t| " + FIND_COURSE);
        view.write("\t|\t-> find course by title");
        view.write("---------------------------------------------------------");

        view.write("\t| " + UPDATE_COURSE_TITLE);
        view.write("\t|\t-> update course title. Title is a unique field");
        view.write("---------------------------------------------------------");

        view.write("\t| " + UPDATE_COURSE_STATUS);
        view.write("\t|\t-> update course status. Possible status values: ");
        view.write("\t|\t-> " + collectCourseStatuses());
        view.write("---------------------------------------------------------");

        view.write("\t| " + SHOW_COURSES);
        view.write("\t|\t-> show all courses");
        view.write("---------------------------------------------------------");

        view.write("\t| " + DELETE_COURSE);
        view.write("\t|\t-> move course to a DELETED status");
        view.write("---------------------------------------------------------");

        view.write("\t| " + CREATE_USER);
        view.write("\t|\t-> create new user, user role will be set as " + UserRole.NEWCOMER + " and user status");
        view.write("\t|\t-> as " + UserStatus.NOT_ACTIVE);
        view.write("---------------------------------------------------------");

        view.write("\t| " + FIND_USER);
        view.write("\t|\t-> find user with specified email");
        view.write("---------------------------------------------------------");

        view.write("\t| " + DELETE_USER_COURSE);
        view.write("\t|\t-> delete user course and move user to NOT_ACTIVE status");
        view.write("---------------------------------------------------------");

        view.write("\t| " + FIND_ALL_USERS_BY_COURSE);
        view.write("\t|\t-> get all users by specified course");
        view.write("---------------------------------------------------------");

        view.write("\t| " + UPDATE_USER_COURSE);
        view.write("\t|\t-> update user course by specified course title");
        view.write("---------------------------------------------------------");

        view.write("\t| " + FIND_ALL_USERS_BY_STATUS);
        view.write("\t|\t-> get all users by specified status ["+ collectUsersStatuses() +"]");
        view.write("---------------------------------------------------------");

        view.write("\t| " + UPDATE_USER_EMAIL);
        view.write("\t|\t-> update user email");
        view.write("---------------------------------------------------------");

        view.write("\t| " + EXIT);
        view.write("\t|\t-> exit application");
        view.write("---------------------------------------------------------");
        view.write("---------------------------------------------------------");
    }

    private String collectCourseStatuses() {
        return Arrays.stream(CourseStatus.values()).map(CourseStatus::getStatus).collect(Collectors.joining(", "));
    }

    private String collectUsersStatuses() {
        return Arrays.stream(UserStatus.values()).map(UserStatus::getStatus).collect(Collectors.joining(", "));
    }
}
