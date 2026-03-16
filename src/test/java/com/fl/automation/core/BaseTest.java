package com.fl.automation.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    private static final String BASE_URL = "https://www.footlocker.com/";

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver();
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}