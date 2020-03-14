package com.courses.management.solution;

import com.courses.management.common.DataAccessObject;

import java.util.List;

public interface SolutionDAO extends DataAccessObject<Solution> {
    List<Solution> getAllByUserId(int id);
    List<Solution> getAllByHomework(int id);
    Solution get(int userId, int homeworkId);
}
