package com.courses.management.user;

public class UsersTest {
    public static User getTestUser() {
        User user = new User();
        user.setFirstName("Oleksandr");
        user.setLastName("Yanov");
        user.setEmail("oleksandr.yanov@email.com");
        user.setStatus(UserStatus.NOT_ACTIVE);
        user.setUserRole(UserRole.NEWCOMER);
        return user;
    }
}
