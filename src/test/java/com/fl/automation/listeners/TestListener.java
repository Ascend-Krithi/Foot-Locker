package com.fl.automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.utils.ExtentManager;
import com.fl.automation.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        test.get().log(Status.FAIL, result.getThrowable());
        
        if (DriverFactory.getDriver() != null) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(DriverFactory.getDriver(), result.getMethod().getMethodName());
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}