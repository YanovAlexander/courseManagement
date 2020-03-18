package com.courses.management.course;

import com.courses.management.common.BaseEntity;
import com.courses.management.homework.Homework;
import com.courses.management.user.User;

import java.util.List;
import java.util.Objects;

public class Course extends BaseEntity {
    private String title;
    private List<User> users;
    private CourseStatus courseStatus;
    private Calendar calendar;
    private List<Homework> homework;

    public Course() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public List<Homework> getHomework() {
        return homework;
    }

    public void setHomework(List<Homework> homework) {
        this.homework = homework;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return title.equals(course.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
