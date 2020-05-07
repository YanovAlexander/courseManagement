package com.courses.management.course;

import java.util.List;

public interface CourseService {

    List<Course> showCourses();
    Course getById(Integer id);
    Course getByTitle(String title);
    Course createCourse(Course course);
}
