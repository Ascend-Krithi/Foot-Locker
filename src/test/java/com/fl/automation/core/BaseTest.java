package com.fl.automation.core;

import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import com.fl.automation.utils.ExtentManager;
import com.fl.automation.utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * BaseTest - Base class for all test classes
 * Handles driver initialization, page object creation, and test lifecycle
 */
@Slf4j
public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;
    protected StoreLocatorHelper storeLocatorHelper;
    protected ExtentTest test;

    @BeforeSuite
    public void beforeSuite() {
        log.info("========== Test Suite Started ==========");
        ExtentManager.createInstance();
    }

    @BeforeMethod
    public void setUp(Method method) {
        log.info("========== Test Started: {} ==========", method.getName());
        
        // Create driver
        driver = DriverFactory.createDriver();
        
        // Initialize page objects
        homePage = new HomePage(driver);
        storeLocatorHelper = new StoreLocatorHelper(driver);
        
        // Create ExtentTest
        String testName = method.getName();
        String description = method.getAnnotation(org.testng.annotations.Test.class).description();
        test = ExtentManager.createTest(testName, description);
        
        log.info("Test setup completed for: {}", testName);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        log.info("========== Test Finished: {} ==========", testName);
        
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                log.error("Test FAILED: {}", testName);
                test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
                
                // Capture screenshot on failure
                if (driver != null) {
                    String screenshotPath = ScreenshotUtil.captureScreenshot(driver, testName);
                    if (screenshotPath != null) {
                        test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
                    }
                }
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                log.info("Test PASSED: {}", testName);
                test.log(Status.PASS, "Test Passed Successfully");
            } else if (result.getStatus() == ITestResult.SKIP) {
                log.warn("Test SKIPPED: {}", testName);
                test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
            }
        } catch (Exception e) {
            log.error("Error in tearDown: {}", e.getMessage());
        } finally {
            // Quit driver
            if (driver != null) {
                DriverFactory.quitDriver();
            }
            ExtentManager.removeTest();
        }
    }

    @AfterSuite
    public void afterSuite() {
        log.info("========== Test Suite Finished ==========");
        ExtentManager.flush();
    }

    /**
     * Logs info message to both logger and ExtentReport
     */
    protected void logInfo(String message) {
        log.info(message);
        if (test != null) {
            test.info(message);
        }
    }

    /**
     * Logs pass message to both logger and ExtentReport
     */
    protected void logPass(String message) {
        log.info("PASS: {}", message);
        if (test != null) {
            test.pass(message);
        }
    }

    /**
     * Logs fail message to both logger and ExtentReport
     */
    protected void logFail(String message) {
        log.error("FAIL: {}", message);
        if (test != null) {
            test.fail(message);
        }
    }
}