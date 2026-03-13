package com.fl.automation.listeners;

import com.aventstack.extentreports.Status;
import com.fl.automation.utils.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());
        System.out.println("Total Tests Run: " + context.getAllTestMethods().length);
        System.out.println("Passed: " + context.getPassedTests().size());
        System.out.println("Failed: " + context.getFailedTests().size());
        System.out.println("Skipped: " + context.getSkippedTests().size());
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.INFO, "Test execution started: " + result.getName());
        }
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Test passed successfully");
        }
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        System.out.println("Failure Reason: " + result.getThrowable());
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.FAIL, "Test failed: " + result.getThrowable());
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.SKIP, "Test skipped: " + result.getThrowable());
        }
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test Failed But Within Success Percentage: " + result.getName());
    }
}