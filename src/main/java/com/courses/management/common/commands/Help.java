package com.courses.management.common.commands;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.InputString;
import com.courses.management.course.CourseStatus;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Help implements Command {
    private View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public String command() {
        return "help";
    }

    @Override
    public void process(InputString input) {
        view.write("-----------------------COMMANDS--------------------------");
        view.write("---------------------------------------------------------");
        view.write("\t| create_course|title");
        view.write("\t|\t-> create a course with a title");
        view.write("---------------------------------------------------------");

        view.write("\t| find_course|title");
        view.write("\t|\t-> find course by title");
        view.write("---------------------------------------------------------");

        view.write("\t| update_course_title|oldTitle|newTitle");
        view.write("\t|\t-> update course title. Title is a unique field");
        view.write("---------------------------------------------------------");

        view.write("\t| update_course_status|title|status");
        view.write("\t|\t-> update course status. Possible status values: ");
        view.write("\t|\t-> " + collectCourseStatuses());
        view.write("---------------------------------------------------------");

        view.write("\t| show_courses");
        view.write("\t|\t-> show all courses");
        view.write("---------------------------------------------------------");

        view.write("\t| delete_course|title");
        view.write("\t|\t-> move course to a DELETED status");
        view.write("---------------------------------------------------------");

        view.write("\t| exit");
        view.write("\t|\t-> exit application");
        view.write("---------------------------------------------------------");
        view.write("---------------------------------------------------------");
    }

    private String collectCourseStatuses() {
        return Arrays.stream(CourseStatus.values()).map(CourseStatus::getStatus).collect(Collectors.joining(", "));
    }
}
