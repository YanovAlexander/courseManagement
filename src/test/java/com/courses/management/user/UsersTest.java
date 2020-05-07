package com.courses.management.user;

import com.courses.management.common.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UsersTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private Users users;

    @Test
    public void testGetAllUsersShouldReturnEmptyList() {
        //given
        when(userRepository.findAll()).thenReturn(List.of());
        //when
        final List<User> users = this.users.getAllUsers();
        //then
        assertThat(users.size()).isEqualTo(0);
    }

    @Test
    public void testGetAllUsersShouldReturnUsersList() {
        //given
        when(userRepository.findAll()).thenReturn(UserHelper.prepareUsersList());
        //when
        final List<User> users = this.users.getAllUsers();
        //then
        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    public void testRegisterUserShouldReturnUser() {
        //given
        final User user = UserHelper.createUser(UserHelper.FIRST_USER_NAME, UserHelper.FIRST_USER_LAST_NAME, UserHelper.FIRST_USER_EMAIL,
                UserHelper.FIRST_USER_PASSWORD);
        //when
        this.users.registerUser(user);
        //then
        assertThat(user.getPassword()).isNotEqualTo(UserHelper.FIRST_USER_PASSWORD);
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testRegisterUserShouldReturnException() {
        //given
        final User user = UserHelper.createUser(UserHelper.FIRST_USER_NAME, UserHelper.FIRST_USER_LAST_NAME, UserHelper.FIRST_USER_EMAIL,
                UserHelper.FIRST_USER_PASSWORD);
        when(userRepository.findByEmail(UserHelper.FIRST_USER_EMAIL)).thenReturn(Optional.of(user));
        //when
        this.users.registerUser(user);
    }
}
