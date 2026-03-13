package com.fl.automation.core;

import com.fl.automation.utils.ExtentManager;
import com.fl.automation.utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    @BeforeSuite
    public void setupSuite() {
        extent = ExtentManager.getInstance();
    }
    
    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver();
        driver.manage().window().maximize();
    }
    
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
        }
        
        if (driver != null) {
            driver.quit();
        }
    }
    
    @AfterSuite
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush();
        }
    }
}