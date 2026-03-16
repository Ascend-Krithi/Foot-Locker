package com.fl.automation.core;

import com.fl.automation.utils.ExtentManager;
import com.fl.automation.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    
    protected WebDriver driver;
    
    @BeforeSuite
    public void beforeSuite() {
        ExtentManager.getInstance();
    }
    
    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            ScreenshotUtil.captureScreenshot(driver, "final_state");
            DriverFactory.quitDriver();
        }
    }
    
    @AfterSuite
    public void afterSuite() {
        ExtentManager.flush();
    }
}