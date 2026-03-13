package com.fl.automation.core;

import com.fl.automation.utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;
    private static final String BASE_URL = "https://www.footlocker.com";

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        DriverFactory.setDriver(browser);
        driver = DriverFactory.getDriver();
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            DriverFactory.quitDriver();
        }
        ExtentManager.removeTest();
    }

    protected void navigateToUrl(String url) {
        driver.get(url);
    }
}