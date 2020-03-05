package com.courses.management.common;

import java.util.List;

public interface DataAccessObject<T extends BaseEntity> {
    void create(T t);
    void update(T t);
    void delete(int id);
    T get(int id);
    List<T> getAll();
}
