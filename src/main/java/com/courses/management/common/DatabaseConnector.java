package com.courses.management.common;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConnector {
    private static final Logger LOG = LogManager.getLogger(DatabaseConnector.class);
    private final HikariDataSource ds;

    public DatabaseConnector() {
        HikariConfig config = new HikariConfig();
        final Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceAsStream = classLoader.getResourceAsStream("application.properties")) {
            properties.load(resourceAsStream);
        } catch (Exception e) {
            LOG.error("Error loading application.properties", e);
            throw new RuntimeException("Error loading application.properties", e);
        }

        config.setJdbcUrl(properties.getProperty("jdbc.url"));
        config.setUsername(properties.getProperty("jdbc.username"));
        config.setPassword(properties.getProperty("jdbc.password"));
        ds = new HikariDataSource(config);
        ds.setMaximumPoolSize(Integer.parseInt(properties.getProperty("jdbc.connection.pool.max.size")));
    }


    public DataSource getDataSource() {
        return ds;
    }
}
