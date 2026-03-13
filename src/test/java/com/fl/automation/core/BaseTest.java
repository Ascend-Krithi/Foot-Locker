package com.fl.automation.core;

import com.aventstack.extentreports.Status;
import com.fl.automation.listeners.TestListener;
import com.fl.automation.utils.ExtentManager;
import com.fl.automation.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class BaseTest {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://www.footlocker.com";

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            if (screenshotPath != null && ExtentManager.getTest() != null) {
                try {
                    ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath);
                } catch (Exception e) {
                    ExtentManager.getTest().log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
                }
            }
        }
        DriverFactory.quitDriver();
    }
}