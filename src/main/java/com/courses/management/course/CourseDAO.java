package com.courses.management.course;

import com.courses.management.common.DataAccessObject;

public interface CourseDAO extends DataAccessObject<Course> {
    Course get(String title);
}
