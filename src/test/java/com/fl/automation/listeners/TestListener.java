package com.fl.automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.fl.automation.utils.ExtentManager;
import com.fl.automation.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Field;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();
    ExtentTest test;

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());

        try {
            Object currentClass = result.getInstance();
            Field field = result.getTestClass().getRealClass().getSuperclass().getDeclaredField("driver");
            field.setAccessible(true);
            WebDriver driver = (WebDriver) field.get(currentClass);
            String path = ScreenshotUtil.capture(driver, result.getMethod().getMethodName());
            test.addScreenCaptureFromPath(path);
        } catch (Exception e) {
        }
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}