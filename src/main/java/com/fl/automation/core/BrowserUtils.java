package com.fl.automation.core;

import org.openqa.selenium.WebDriver;

public class BrowserUtils {
    public static void navigateTo(WebDriver driver, String url) {
        driver.get(url);
    }

    public static String getTitle(WebDriver driver) {
        return driver.getTitle();
    }
}