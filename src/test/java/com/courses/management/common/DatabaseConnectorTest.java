package com.courses.management.common;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConnectorTest {
    private static final Logger LOG = LogManager.getLogger(DatabaseConnectorTest.class);
    private final HikariDataSource ds;

    public DatabaseConnectorTest() {
        HikariConfig config = new HikariConfig();
        final Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceAsStream = classLoader.getResourceAsStream("application-test.properties")) {
            properties.load(resourceAsStream);
        } catch (Exception e) {
            LOG.error("Error loading application-test.properties", e);
            throw new RuntimeException("Error loading application-test.properties", e);
        }

        config.setJdbcUrl(properties.getProperty("jdbc.url"));
        ds = new HikariDataSource(config);
    }


    public DataSource getDataSource() {
        return ds;
    }
}
