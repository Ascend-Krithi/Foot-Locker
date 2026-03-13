package com.fl.automation.core;

import com.fl.automation.utils.ExtentManager;
import com.fl.automation.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class BaseTest {
    
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;
    
    protected static final String BASE_URL = "https://www.footlocker.com";
    protected static final String MARKETPLACE_URL = "https://marketplace.example.com";
    protected static final String ECO_HOME_HUB_URL = "https://ecohomehub.example.com";
    
    @BeforeSuite
    public void setupSuite() {
        extent = ExtentManager.getInstance();
    }
    
    @BeforeMethod
    public void setup(ITestResult result) {
        DriverFactory.setDriver("chrome");
        driver = DriverFactory.getDriver();
        
        String testName = result.getMethod().getMethodName();
        String testDescription = result.getMethod().getDescription();
        test = extent.createTest(testName, testDescription);
        ExtentManager.setTest(test);
        
        test.log(Status.INFO, "Browser launched successfully");
        test.log(Status.INFO, "Test execution started: " + testName);
    }
    
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel("Test Case FAILED: " + result.getName(), ExtentColor.RED));
            test.log(Status.FAIL, "Failure Reason: " + result.getThrowable());
            
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            if (screenshotPath != null) {
                try {
                    test.addScreenCaptureFromPath(screenshotPath);
                } catch (Exception e) {
                    test.log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
                }
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel("Test Case PASSED: " + result.getName(), ExtentColor.GREEN));
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, MarkupHelper.createLabel("Test Case SKIPPED: " + result.getName(), ExtentColor.YELLOW));
            test.log(Status.SKIP, "Skip Reason: " + result.getThrowable());
        }
        
        ExtentManager.removeTest();
        DriverFactory.quitDriver();
        test.log(Status.INFO, "Browser closed successfully");
    }
    
    @AfterSuite
    public void tearDownSuite() {
        ExtentManager.flush();
        System.out.println("Extent Report generated at: " + ExtentManager.getReportPath());
    }
}