package com.courses.management.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static Properties properties;
    private static final String FOLDER_PATH = "folder_path";

    public static void init (String path) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = classLoader.getResourceAsStream(path)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static String getFolderPath() {
        return properties.getProperty(FOLDER_PATH);
    }
}
