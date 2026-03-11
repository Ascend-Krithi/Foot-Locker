package com.fl.automation.core;

import com.fl.automation.utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.footlocker.com");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
        ExtentManager.flush();
    }
}
