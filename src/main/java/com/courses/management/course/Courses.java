package com.courses.management.course;

import com.courses.management.common.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Courses {
    private static final Logger LOG = LogManager.getLogger(Courses.class);
    private CourseRepository courseRepository;

    public Courses(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> showCourses() {
        return courseRepository.findAll();
    }

    public Course getById(Integer id) {
        final Course course = courseRepository.findById(id).get();
        return course;
    }

    public Course getByTitle(String title) {
        return courseRepository.getByTitle(title);
    }

    public Course createCourse(Course course) {
        courseRepository.save(course);
        return course;
    }
}
