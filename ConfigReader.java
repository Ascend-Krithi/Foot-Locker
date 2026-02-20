package com.fl.automation.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();
    private static boolean loaded = false;

    public static void loadConfig() {
        if (!loaded) {
            try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
                properties.load(fis);
                loaded = true;
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config.properties", e);
            }
        }
    }

    public static String get(String key) {
        if (!loaded) {
            loadConfig();
        }
        return properties.getProperty(key);
    }
}
