package com.courses.management.common.commands;

import com.courses.management.common.Command;
import com.courses.management.common.View;

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
    public void process() {
        view.write("-------------------------------------------------");
        view.write("------------------List of commands---------------");
        view.write("    Command     |           Description          ");
        view.write("create_course   | create a course with a title");
        view.write("exit   | exit application");
        view.write("-------------------------------------------------");
    }
}
