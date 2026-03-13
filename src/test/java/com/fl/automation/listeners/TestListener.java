package com.fl.automation.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = BaseTest.extent.createTest(result.getMethod().getMethodName());
        BaseTest.test.set(extentTest);
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest.test.get().log(Status.PASS, "Test Passed");
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest.test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        BaseTest.test.get().log(Status.SKIP, "Test Skipped");
    }
}