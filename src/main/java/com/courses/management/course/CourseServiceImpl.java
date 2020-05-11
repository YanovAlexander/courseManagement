package com.courses.management.course;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CourseServiceImpl implements CourseService {
    private static final Logger LOG = LogManager.getLogger(CourseServiceImpl.class);
    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> showCourses() {
        LOG.debug("showCourses: ");
        return courseRepository.findAll();
    }

    @Override
    public Course getById(Integer id) {
        LOG.debug(String.format("getById: id=%d", id));
        return courseRepository.findById(id)
                .orElse(new Course());
    }

    @Override
    public Course getByTitle(String title) {
        LOG.debug(String.format("getByTitle: title=%s", title));
        return courseRepository.getByTitle(title);
    }

    @Override
    public Course createCourse(Course course) {
        LOG.debug(String.format("createCourse: courseTitle=%s", course.getTitle()));

        if (Objects.nonNull(getByTitle(course.getTitle()))) {
            throw new CourseAlreadyExistsError(
                    String.format("course with title %s already exists", course.getTitle()));
        }

        courseRepository.save(course);
        return course;
    }
}
