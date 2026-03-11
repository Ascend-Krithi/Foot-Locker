package com.fl.automation.listeners;

import com.fl.automation.utils.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import lombok.extern.slf4j.Slf4j;

/**
 * TestListener - TestNG listener for test execution events
 */
@Slf4j
public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        log.info("========== Test Suite '{}' Started ==========", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("========== Test Suite '{}' Finished ==========", context.getName());
        log.info("Total Tests: {}", context.getAllTestMethods().length);
        log.info("Passed: {}", context.getPassedTests().size());
        log.info("Failed: {}", context.getFailedTests().size());
        log.info("Skipped: {}", context.getSkippedTests().size());
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test Started: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test Passed: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test Failed: {}", result.getMethod().getMethodName());
        log.error("Failure Reason: {}", result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test Skipped: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.warn("Test Failed but within success percentage: {}", result.getMethod().getMethodName());
    }
}