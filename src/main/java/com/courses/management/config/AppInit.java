package com.courses.management.config;

import com.courses.management.common.PropertiesUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInit implements ServletContextListener {
    private static final String APPLICATION_PROPERTIES_FILENAME = "application.properties";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Init hibernate");
        HibernateDatabaseConnector.init();
        System.out.println("Init properties");
        PropertiesUtil.init(APPLICATION_PROPERTIES_FILENAME);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Destroy hibernate");
        HibernateDatabaseConnector.destroy();
    }
}
