package com.courses.management;

import com.courses.management.common.Command;
import com.courses.management.common.DatabaseConnectorTest;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.InputString;
import com.courses.management.user.*;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class IntegrationTest {
    private DatabaseConnectorTest dbConnector = new DatabaseConnectorTest();
    private View view = mock(View.class);
    private UserDAO userDAO = new UserDAOImpl(dbConnector.getDataSource());
    private Command createUserCommand = new CreateUser(view, userDAO);
    private Command findUserCommand = new FindUser(view, userDAO);

    @Test
    public void testCreateUser() {
        InputString inputString = new InputString("create_user|Oleksandr|Yanov|oleksandr.yanov@email.com");
        createUserCommand.process(inputString);
        findUserCommand.process(new InputString("find_user|oleksandr.yanov@email.com"));
    }
}
