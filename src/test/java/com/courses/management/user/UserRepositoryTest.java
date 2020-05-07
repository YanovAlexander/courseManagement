package com.courses.management.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource({"classpath:application-test.properties"})
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmailShouldReturnUser() {
        //given
        prepareData();
        //when
        final User user = userRepository.findByEmail(UserHelper.FIRST_USER_EMAIL).get();
        //then
        assertThat(user.getFirstName()).isEqualTo(UserHelper.FIRST_USER_NAME);
        assertThat(user.getLastName()).isEqualTo(UserHelper.FIRST_USER_LAST_NAME);
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindByEmailShouldReturnNull() {
        //given
        prepareData();
        //when
        User user = userRepository.findByEmail(UserHelper.INCORRECT_EMAIL).get();
    }

    private void prepareData() {
        UserHelper.prepareUsersList().forEach(user -> {
                    testEntityManager.persist(user);
                });
        testEntityManager.flush();
    }

}
