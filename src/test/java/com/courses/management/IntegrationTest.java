package com.courses.management;

import com.courses.management.common.Console;
import com.courses.management.common.DatabaseConnectorTest;
import com.courses.management.common.MainController;
import com.courses.management.common.View;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {
    private MainController mainController;
    private CustomInputStream in;
    private ByteArrayOutputStream out;
    private DatabaseConnectorTest dbConnector;

    @Before
    public void setup() {
        this.dbConnector = new DatabaseConnectorTest();
//        this.mainController = new MainController(new Console(), dbConnector.getDataSource(),);
        this.in = new CustomInputStream();
        this.out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
    }

    @After
    public void tearDown() throws SQLException {
        clearDatabase();
    }

    @Test
    public void testHelpCommand() {
        //given
        in.add("help");
        in.add("exit");

        //when
        mainController.read();

        //then
        assertEquals("Welcome\n" +
                "Enter a command or use help to list all available commands:\n" +
                "-----------------------COMMANDS--------------------------\n" +
                "---------------------------------------------------------\n" +
                "\t| create_course|title\n" +
                "\t|\t-> create a course with a title\n" +
                "---------------------------------------------------------\n" +
                "\t| find_course|title\n" +
                "\t|\t-> find course by title\n" +
                "---------------------------------------------------------\n" +
                "\t| update_course_title|oldTitle|newTitle\n" +
                "\t|\t-> update course title. Title is a unique field\n" +
                "---------------------------------------------------------\n" +
                "\t| update_course_status|title|status\n" +
                "\t|\t-> update course status. Possible status values: \n" +
                "\t|\t-> NOT_STARTED, IN_PROGRESS, FINISHED, DELETED\n" +
                "---------------------------------------------------------\n" +
                "\t| show_courses\n" +
                "\t|\t-> show all courses\n" +
                "---------------------------------------------------------\n" +
                "\t| delete_course|title\n" +
                "\t|\t-> move course to a DELETED status\n" +
                "---------------------------------------------------------\n" +
                "\t| create_user|firstName|lastName|email\n" +
                "\t|\t-> create new user, user role will be set as NEWCOMER and user status\n" +
                "\t|\t-> as NOT_ACTIVE\n" +
                "---------------------------------------------------------\n" +
                "\t| find_user|email\n" +
                "\t|\t-> find user with specified email\n" +
                "---------------------------------------------------------\n" +
                "\t| delete_user_course|userEmail\n" +
                "\t|\t-> delete user course and move user to NOT_ACTIVE status\n" +
                "---------------------------------------------------------\n" +
                "\t| find_users_by_course_title|courseTitle\n" +
                "\t|\t-> get all users by specified course\n" +
                "---------------------------------------------------------\n" +
                "\t| update_user_course|userEmail|courseTitle\n" +
                "\t|\t-> update user course by specified course title\n" +
                "---------------------------------------------------------\n" +
                "\t| find_users_by_user_status|status\n" +
                "\t|\t-> get all users by specified status [ACTIVE, NOT_ACTIVE]\n" +
                "---------------------------------------------------------\n" +
                "\t| update_user_email|oldEmail|newEmail\n" +
                "\t|\t-> update user email\n" +
                "---------------------------------------------------------\n" +
                "\t| exit\n" +
                "\t|\t-> exit application\n" +
                "---------------------------------------------------------\n" +
                "---------------------------------------------------------\n" +
                "Good bye!\n", getData());
    }

    @Test
    public void testCreateCourseWithCorrectInputParameters() {
        //given
        in.add("create_course|Title_Java");
        in.add("find_course|Title_Java");
        in.add("exit");

        //when
        mainController.read();

        //then
        assertEquals("Welcome\n" +
                "Enter a command or use help to list all available commands:\n" +
                "Course created with title - Title_Java\n" +
                "Course:\n" +
                "\t title = Title_Java\n" +
                "\t status = NOT_STARTED\n" +
                "Good bye!\n", getData());
    }

    public String getData() {
        try {
            String result = new String(out.toByteArray(), "UTF-8").replaceAll("\r\n", "\n");
            out.reset();
            return result;
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }

    private void clearDatabase() throws SQLException {
        try (Connection connection = dbConnector.getDataSource().getConnection();
             Statement statement = connection.createStatement()){
            statement.execute("DROP ALL OBJECTS;");
        }
    }
}
