package com.fl.automation.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    public static void initProperties(String filePath) {
        prop = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + filePath, e);
        }
    }

    public static String getProperty(String key) {
        if (prop == null) {
            throw new IllegalStateException("Properties not initialized. Call initProperties() first.");
        }
        return prop.getProperty(key);
    }
}
