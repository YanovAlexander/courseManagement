package com.courses.management.user;

import com.courses.management.common.DataAccessObject;

public interface UserDAO extends DataAccessObject<User> {
    User get(String email);
}
