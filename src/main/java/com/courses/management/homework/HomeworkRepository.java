package com.courses.management.homework;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkRepository extends CrudRepository<Homework, Integer> {
}
