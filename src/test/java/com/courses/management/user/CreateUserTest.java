package com.courses.management.user;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.InputString;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CreateUserTest {
    private UserDAO dao;
    private Command command;
    private View view;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        this.view = mock(View.class);
        this.dao = mock(UserDAO.class);
        this.command = new CreateUser(view, dao);
    }

    @Test
    public void testCanProcessWithCorrectCommand() {
        //given
        InputString inputString = new InputString("create_user|Oleksandr|Yanov|oleksandr.yanov@email.com");
        //when
        boolean result = command.canProcess(inputString);
        //then
        assertTrue(result);
    }

    @Test
    public void testCanProcessWithIncorrectCommand() {
        //given
        InputString inputString = new InputString("create_users|Oleksandr|Yanov|oleksandr.yanov@email.com");
        //when
        boolean result = command.canProcess(inputString);
        //then
        assertFalse(result);
    }

    @Test
    public void testProcessWithIncorrectEmail() {
        //given
        InputString inputString = new InputString("create_user|Oleksandr|Yanov|oleksandr.yanov@email");
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Wrong email address oleksandr.yanov@email");
        //when
        command.process(inputString);
    }

    @Test
    public void testProcessWithDuplicateEmail() {
        //given
        InputString inputString = new InputString("create_user|Oleksandr|Yanov|oleksandr.yanov@email.com");
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("User with email oleksandr.yanov@email.com already exists");
        //when
        when(dao.get("oleksandr.yanov@email.com")).thenReturn(new User());
        command.process(inputString);
    }

    @Test
    public void testProcessWithCorrectData() {
        //given
        InputString inputString = new InputString("create_user|Oleksandr|Yanov|oleksandr.yanov@email.com");
        User user = UsersTest.getTestUser();
        //when
        when(dao.get("oleksandr.yanov@email.com")).thenReturn(null);
        command.process(inputString);
        //then
        verify(dao, times(1)).create(user);
        verify(dao, times(1)).get("oleksandr.yanov@email.com");
        verify(view).write(String.format("User %s %s created!", user.getFirstName(), user.getLastName()));
    }

    @Test
    public void testProcessEmptyFirstName() {
        //given
        InputString inputString = new InputString("create_user||Yanov|oleksandr.yanov@email.com");
        User user = UsersTest.getTestUser();
        user.setFirstName(null);
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("User first name can't be empty");
        //when
        command.process(inputString);
    }

    @Test
    public void testProcessEmptyLastName() {
        //given
        InputString inputString = new InputString("create_user|Oleksandr||oleksandr.yanov@email.com");
        User user = UsersTest.getTestUser();
        user.setLastName(null);
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("User last name can't be empty");
        //when
        command.process(inputString);
    }

    @Test
    public void testProcessEmptyEmail() {
        //given
        InputString inputString = new InputString("create_user|Oleksandr|Yanov| |");
        User user = UsersTest.getTestUser();
        user.setEmail(null);
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Wrong email address  ");
        //when
        command.process(inputString);
    }
}
