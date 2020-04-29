package com.courses.management.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Users {
    private static final Logger LOG = LogManager.getLogger(Users.class);

    private UserRepository repository;

    public Users(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        LOG.debug("getAllUsers: ");
        return repository.findAll();
    }

    public User getUser(Integer id) {
        LOG.debug(String.format("getUser: id=%d", id));
        return repository.findById(id)
                .orElseThrow(() -> new UserNotExistsException(String.format("User with id = %s not found", id)));
    }

    public User getUser(String email) {
        LOG.debug(String.format("getUser: email=%d", email));
        final User user = repository.findByEmail(email)
                .orElseThrow(() ->  new UserNotExistsException("User not found by specified email"));
        return user;
    }
}
