package com.courses.management.user;

public class UserAlreadyExistsException  extends RuntimeException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
