package com.courses.management.user;

import com.courses.management.common.BaseEntity;
import com.courses.management.course.Course;
import com.courses.management.course.Homework;

import java.util.List;

public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private UserRole userRole;
    private UserStatus status;
    private Course course;
    private List<Homework> homework;

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Course getCourse() {
        return course;
    }

    public void setGroups(Course course) {
        this.course = course;
    }

    public List<Homework> getHomework() {
        return homework;
    }

    public void setHomework(List<Homework> homework) {
        this.homework = homework;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
