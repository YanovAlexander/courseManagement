package com.courses.management.common;

import com.courses.management.course.CreateCourse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class MainController {
    private static final Logger LOG = LogManager.getLogger(MainController.class);
    private View view;
    private List<Command> commands;

    public MainController(View view) {
        this.view = view;
        this.commands = Arrays.asList(
                new CreateCourse(view),
                new Help(view)
        );
    }

    public void read() {
        view.write("Welcome");
        while (true) {
            view.write("Enter a command or use help to list all available commands:");
            String read = view.read();
            doCommand(read);
        }
    }

    private void doCommand(String input) {
    LOG.debug(String.format("doCommand: input=%s", input));
        for (Command command : commands) {
            if (command.canProcess(input)) {
                command.process();
                break;
            }
        }
    }
}
