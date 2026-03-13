package com.fl.automation.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
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
        ExtentManager.getInstance().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
        test.info("Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, 
            MarkupHelper.createLabel("Test PASSED: " + result.getMethod().getMethodName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().log(Status.FAIL, 
            MarkupHelper.createLabel("Test FAILED: " + result.getMethod().getMethodName(), ExtentColor.RED));
        ExtentManager.getTest().fail(result.getThrowable());
        
        if (DriverFactory.getDriver() != null) {
            String screenshotBase64 = ScreenshotUtil.captureBase64Screenshot(DriverFactory.getDriver());
            if (screenshotBase64 != null) {
                ExtentManager.getTest().addScreenCaptureFromBase64String(screenshotBase64, "Failure Screenshot");
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().log(Status.SKIP, 
            MarkupHelper.createLabel("Test SKIPPED: " + result.getMethod().getMethodName(), ExtentColor.YELLOW));
        ExtentManager.getTest().skip(result.getThrowable());
    }
}