package com.courses.management.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class DatabaseConnector {
    private static final Logger LOG = LogManager.getLogger(DatabaseConnector.class);
    private static HikariDataSource ds;

    public synchronized static void init(String fileName) {
        if (ds != null) {
            return;
        }

        if (fileName.isEmpty()) {
            throw new NullPointerException("init method. configFileName is null");
        }

        HikariConfig config = new HikariConfig();
        final Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceAsStream = classLoader.getResourceAsStream(fileName)) {
            properties.load(resourceAsStream);
        } catch (Exception e) {
            LOG.error("Error loading application.properties", e);
            throw new RuntimeException("Error loading application.properties", e);
        }

        try {
            Class.forName(properties.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException ex) {
            LOG.error("Error loading postgres driver", ex);
            throw new RuntimeException("Error loading postgres driver", ex);
        }

        config.setJdbcUrl(properties.getProperty("jdbc.url"));
        config.setUsername(properties.getProperty("jdbc.username"));
        config.setPassword(properties.getProperty("jdbc.password"));
        ds = new HikariDataSource(config);
        ds.setMaximumPoolSize(Integer.parseInt(properties.getProperty("jdbc.connection.pool.max.size")));
    }

    private DatabaseConnector() {

    }


    public static DataSource getDataSource() {
        return ds;
    }

    public synchronized static void destroy() {
        if (Objects.isNull(ds)) {
            return;
        }

        ds.close();
        ds = null;
    }
}
