package com.fl.automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fl.automation.core.BaseTest;
import com.fl.automation.utils.ExtentManager;
import com.fl.automation.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private ExtentReports extent = ExtentManager.getInstance();
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result){
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result){
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result){

        test.get().log(Status.FAIL, result.getThrowable());

        Object testClass = result.getInstance();

        if(testClass instanceof BaseTest){

            String path = ScreenshotUtil.capture(((BaseTest)testClass).driver, result.getMethod().getMethodName());

            if(path != null){
                test.get().addScreenCaptureFromPath(path);
            }
        }
    }

    @Override
    public void onFinish(ITestContext context){
        extent.flush();
    }
}
