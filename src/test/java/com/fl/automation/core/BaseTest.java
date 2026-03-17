package com.fl.automation.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // FIX: Navigate to a category page instead of homepage.
        // On the homepage, "Select my store" navigates away instead of
        // opening the Find a Store modal with the search input.
        // On a category page, the full modal opens correctly as confirmed
        // from the live page screenshot.
        driver.get("https://www.footlocker.com/en/category/shoes/sneakers.html");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
