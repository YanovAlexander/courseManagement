package com.courses.management.common;

import com.courses.management.common.exceptions.ErrorMessage;
import com.courses.management.course.Course;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Validator {
    public static <T> List<ErrorMessage> validateEntity(T entity) {
        List<ErrorMessage> errors = new ArrayList<>();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        final javax.validation.Validator validator = validatorFactory.getValidator();
        final Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            violations.forEach(violation -> {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setField(violation.getPropertyPath().toString());
                errorMessage.setError(violation.getMessage());
                errors.add(errorMessage);
            });
        }
        return errors;
    }
}
