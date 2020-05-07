package com.courses.management.user;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(Integer id);

    User getUser(String email);

    void registerUser(User user);
}
