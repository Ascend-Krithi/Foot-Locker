package com.fl.automation.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.utils.ExtentManager;
import com.fl.automation.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtentManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
        ExtentManager.getTest().log(Status.INFO, "Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        ExtentManager.getTest().log(Status.FAIL, result.getThrowable());
        
        try {
            String screenshotPath = ScreenshotUtil.captureScreenshot(
                DriverFactory.getDriver(), 
                result.getMethod().getMethodName()
            );
            if (screenshotPath != null) {
                ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath);
            }
        } catch (Exception e) {
            ExtentManager.getTest().log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flush();
    }
}