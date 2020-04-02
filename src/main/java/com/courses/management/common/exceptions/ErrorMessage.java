package com.courses.management.common.exceptions;

import java.util.List;

public class ErrorMessage {
    private String field;
    private String error;

    public ErrorMessage(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public ErrorMessage() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
