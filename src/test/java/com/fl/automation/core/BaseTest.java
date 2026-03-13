package com.fl.automation.core;

import com.fl.automation.utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import com.fl.automation.listeners.TestListener;

@Listeners(TestListener.class)
public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }
}