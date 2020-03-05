package com.courses.management.common;

public abstract class DataAccessObject<T extends BaseEntity> {
    public abstract void create(T t);
}
