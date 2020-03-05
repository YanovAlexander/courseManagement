package com.courses.management.homework;

import java.util.List;

public interface HomeworkDAO {
    List<Homework> getAll(int courseId);
}
