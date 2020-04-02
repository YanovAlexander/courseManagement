package com.courses.management.common;

import com.courses.management.common.commands.Exit;
import com.courses.management.common.commands.Help;
import com.courses.management.common.commands.util.InputString;
import com.courses.management.common.exceptions.ExitException;
import com.courses.management.course.*;
import com.courses.management.user.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

public class MainController {
    private static final Logger LOG = LogManager.getLogger(MainController.class);
    private View view;
    private List<Command> commands;

    public MainController(View view, DataSource dataSource, SessionFactory sessionFactory) {
        this.view = view;
        final CourseDAOImpl courseDAO = new CourseDAOImpl(dataSource, sessionFactory);
        final UserDAOImpl userDAO = new UserDAOImpl(dataSource);
        this.commands = Arrays.asList(
                new CreateCourse(view, courseDAO),
                new Help(view),
                new FindCourse(view, courseDAO),
                new UpdateCourseStatus(view, courseDAO),
                new UpdateCourseTitle(view, courseDAO),
                new ShowCourses(view, courseDAO),
                new DeleteCourse(view, courseDAO),
                new CreateUser(view, userDAO),
                new FindUser(view, userDAO),
                new Exit(view),
                new DeleteUserCourse(view, userDAO),
                new FindAllUsersByCourse(view, userDAO),
                new FindUsersByStatus(view, userDAO),
                new UpdateUserCourse(view, userDAO, courseDAO),
                new UpdateUserEmail(view, userDAO)
        );
    }

    public void read() {
        view.write("Welcome");
        view.write("Enter a command or use help to list all available commands:");
        try {
            doCommand();
        } catch (ExitException e) {
            /*NOP*/
        }
    }

    private void doCommand() {
        while (true) {
            InputString entry = new InputString(view.read());
            for (Command command : commands) {
                try {
                    if (command.canProcess(entry)) {
                        entry.validateParameters(command.command());
                        command.process(entry);
                        break;
                    }
                } catch (Exception e) {
                    if (e instanceof ExitException) {
                        throw e;
                    }
                    LOG.warn(String.format("doCommand. WARN %s", e.getMessage()), e);
                    printError(e);
                    break;
                }
            }
        }
    }

    private void printError(Exception e) {
        String message = e.getMessage();
        view.write("Error because of " + message);
        view.write("Please try again.");
    }
}
