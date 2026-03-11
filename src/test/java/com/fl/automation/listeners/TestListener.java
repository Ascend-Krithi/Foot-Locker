package com.fl.automation.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = BaseTest.extent.createTest(result.getMethod().getMethodName());
        if (result.getInstance() instanceof BaseTest) {
            ((BaseTest) result.getInstance()).setExtentTest(extentTest);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (result.getInstance() instanceof BaseTest) {
            ExtentTest extentTest = ((BaseTest) result.getInstance()).getExtentTest();
            if (extentTest != null) {
                extentTest.log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getInstance() instanceof BaseTest) {
            ExtentTest extentTest = ((BaseTest) result.getInstance()).getExtentTest();
            if (extentTest != null) {
                extentTest.log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
                extentTest.log(Status.FAIL, result.getThrowable());
                
                String screenshotPath = ScreenshotUtil.captureScreenshot(
                    DriverFactory.getDriver(), 
                    result.getMethod().getMethodName()
                );
                if (screenshotPath != null) {
                    extentTest.addScreenCaptureFromPath(screenshotPath);
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (result.getInstance() instanceof BaseTest) {
            ExtentTest extentTest = ((BaseTest) result.getInstance()).getExtentTest();
            if (extentTest != null) {
                extentTest.log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
            }
        }
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}