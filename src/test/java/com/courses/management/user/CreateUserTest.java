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

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        View view = mock(View.class);
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
        User user = new User();
        user.setFirstName("Oleksandr");
        user.setLastName("Yanov");
        user.setEmail("oleksandr.yanov@email.com");
        user.setStatus(UserStatus.NOT_ACTIVE);
        user.setUserRole(UserRole.NEWCOMER);
        //when
        when(dao.get("oleksandr.yanov@email.com")).thenReturn(null);
        command.process(inputString);
        //then
        verify(dao, times(1)).create(user);
        verify(dao, times(1)).get("oleksandr.yanov@email.com");
    }
}
