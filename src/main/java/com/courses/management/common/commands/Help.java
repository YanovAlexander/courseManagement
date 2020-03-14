package com.courses.management.common.commands;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.InputString;

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
        view.write("\t| exit");
        view.write("\t|\t-> exit application");
        view.write("---------------------------------------------------------");
    }
}
