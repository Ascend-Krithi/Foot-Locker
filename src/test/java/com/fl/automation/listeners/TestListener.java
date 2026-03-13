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
    public void onFinish(ITestContext context) {
        ExtentManager.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
        ExtentManager.getTest().log(Status.INFO, "Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
        ExtentManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
        ExtentManager.getTest().log(Status.FAIL, result.getThrowable());
        
        if (DriverFactory.getDriver() != null) {
            String base64Screenshot = ScreenshotUtil.getBase64Screenshot(DriverFactory.getDriver());
            ExtentManager.getTest().addScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot");
        }
        ExtentManager.removeTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
        ExtentManager.removeTest();
    }
}