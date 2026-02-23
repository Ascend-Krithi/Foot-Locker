package com.fl.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    public static Properties getProperties() {
        if (prop == null) {
            prop = new Properties();
            try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
                prop.load(fis);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config.properties", e);
            }
        }
        return prop;
    }
}