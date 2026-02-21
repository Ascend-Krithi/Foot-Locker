package com.fl.automation.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();
    private static boolean isLoaded = false;

    private static void loadProperties() {
        if (!isLoaded) {
            try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
                properties.load(fis);
                isLoaded = true;
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config.properties", e);
            }
        }
    }

    public static String getProperty(String key) {
        loadProperties();
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        loadProperties();
        return properties.getProperty(key, defaultValue);
    }
}
