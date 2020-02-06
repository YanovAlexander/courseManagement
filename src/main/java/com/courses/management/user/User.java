package com.courses.management.user;

import com.courses.management.course.Course;

import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private UserRole userRole;
    private List<Course> groups;

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

    public List<Course> getGroups() {
        return groups;
    }

    public void setGroups(List<Course> groups) {
        this.groups = groups;
    }
}
