package com.courses.management.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class Users {
    private static final Logger LOG = LogManager.getLogger(Users.class);

    private UserRepository repository;
    private BCryptPasswordEncoder encoder;

    public Users(UserRepository repository) {
        this.repository = repository;
    }

    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
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
        LOG.debug(String.format("getUser: email=%s", email));
        final User user = repository.findByEmail(email)
                .orElseThrow(() ->  new UserNotExistsException("User not found by specified email"));
        return user;
    }

    public void registerUser(User user) {
        if (emailExists(user.getEmail())) {
            throw new UserAlreadyExistsException("There is an account with that email address: " + user.getEmail());
        }
        user.setUserRole(UserRole.ROLE_NEWCOMER);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        repository.save(user);
    }

    private boolean emailExists(String email) {
        return repository.findByEmail(email).isPresent();
    }
}
