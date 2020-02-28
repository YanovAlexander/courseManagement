package com.courses.management.common;

import java.sql.Connection;

public abstract class DataAccessObject<T extends BaseEntity> {
    protected Connection connection;

    public DataAccessObject(Connection connection) {
        this.connection = connection;
    }

    public abstract void create(T t);
}
