package com.courses.management.user;

import javax.sql.DataSource;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private DataSource dataSource;

    public UserDAOImpl (DataSource ds) {
        this.dataSource = ds;
    }
    @Override
    public void create(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User get(String email) {
        return null;
    }
}
