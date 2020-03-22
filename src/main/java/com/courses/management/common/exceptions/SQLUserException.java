package com.courses.management.common.exceptions;

public class SQLUserException  extends RuntimeException {
    public SQLUserException(String message) {
        super(message);
    }
}