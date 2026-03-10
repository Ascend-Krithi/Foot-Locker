package com.fl.automation.core;

import com.fl.automation.utils.ExtentManager;
import com.fl.automation.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtil.takeScreenshot(driver, result.getName());
        }
        DriverFactory.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush();
        }
    }
}
