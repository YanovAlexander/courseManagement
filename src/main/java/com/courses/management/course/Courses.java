package com.courses.management.course;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

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
        return courseRepository.findById(id)
                .orElse(new Course());
    }

    public Course getByTitle(String title) {
        return courseRepository.getByTitle(title);
    }

    public Course createCourse(Course course) {
        if (Objects.isNull(getByTitle(course.getTitle()))) {
            throw new CourseAlreadyExistsError(
                    String.format("course with title %s already exists", course.getTitle()));
        }

        courseRepository.save(course);
        return course;
    }
}
