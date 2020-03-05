package com.courses.management.solution;

import java.util.List;

public interface SolutionDAO {
    List<Solution> getAllByUserId(int id);
    List<Solution> getAllByHomework(int id);
    Solution get(int userId, int homeworkId);
}
