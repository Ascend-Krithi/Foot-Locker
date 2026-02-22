package com.fl.automation.core;

import org.openqa.selenium.WebDriver;

public class BrowserUtils {
    public static void maximize(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public static void navigateTo(WebDriver driver, String url) {
        driver.get(url);
    }
}
