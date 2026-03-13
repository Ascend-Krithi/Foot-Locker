package com.fl.automation.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    // ThreadLocal ensures each test thread gets its own WebDriver
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        WebDriver localDriver = new ChromeDriver();
        localDriver.manage().window().maximize();
        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.set(localDriver);
        driver.get("https://www.footlocker.com"); // Base URL
    }

    @AfterMethod
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    // Accessor for child classes
    public WebDriver getDriver() {
        return driver.get();
    }
}
