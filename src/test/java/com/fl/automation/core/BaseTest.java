package com.fl.automation.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    // Keep driver private
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Initialize driver
    public void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    // Public getter to access driver safely
    public WebDriver getDriver() {
        return driver.get();
    }

    // Quit driver
    public void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    // Example: start driver
    public void initializeDriver() {
        WebDriver webDriver = new ChromeDriver();
        setDriver(webDriver);
    }
}
