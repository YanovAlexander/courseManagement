package com.courses.management.common;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {
    private final HikariDataSource ds;

    public DatabaseConnector() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/course_management");
        config.setUsername("postgres");
        config.setPassword("admin");
        ds = new HikariDataSource(config);
        ds.setMaximumPoolSize(10);
    }

    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to Connection Pool ", e);
        }
    }
}
