package com.courses.management.homework;

import com.courses.management.common.DataAccessObject;

import java.util.List;

public interface HomeworkDAO extends DataAccessObject<Homework> {
    List<Homework> getAll(int courseId);
}
