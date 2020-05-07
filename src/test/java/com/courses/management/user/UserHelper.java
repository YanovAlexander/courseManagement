package com.courses.management.user;

import java.util.List;

public class UserHelper {
    public static final String FIRST_USER_NAME = "Barack";
    public static final String FIRST_USER_LAST_NAME = "Obama";
    public static final String FIRST_USER_EMAIL = "barak@gmail.com";
    public static final String FIRST_USER_PASSWORD = "123";
    public static final String SECOND_USER_NAME = "Donald";
    public static final String SECOND_USER_LAST_NAME = "Trump";
    public static final String SECOND_USER_EMAIL = "donald@gmail.com";
    public static final String SECOND_USER_PASSWORD = "123";
    public static final String INCORRECT_EMAIL = "test@gmail.com";


    public static User createUser(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setStatus(UserStatus.NOT_ACTIVE);
        user.setUserRole(UserRole.ROLE_NEWCOMER);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        return user;
    }


    public static List<User> prepareUsersList() {
        return List.of(UserHelper.createUser(FIRST_USER_NAME, FIRST_USER_LAST_NAME, FIRST_USER_EMAIL,
                FIRST_USER_PASSWORD),
                UserHelper.createUser(SECOND_USER_NAME, SECOND_USER_LAST_NAME, SECOND_USER_EMAIL,
                        SECOND_USER_PASSWORD));
    }
}
