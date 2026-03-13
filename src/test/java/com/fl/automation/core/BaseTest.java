package com.fl.automation.core;

import com.fl.automation.utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl;

    @BeforeMethod
    @Parameters({"browser", "url"})
    public void setUp(@Optional("chrome") String browser, @Optional("https://www.footlocker.com") String url) {
        DriverFactory.setDriver(browser);
        driver = DriverFactory.getDriver();
        baseUrl = url;
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