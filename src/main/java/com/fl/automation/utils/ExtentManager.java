package com.fl.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static void createInstance() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Foot Locker Automation Report");
        sparkReporter.config().setReportName("Store Locator Test Results");
        sparkReporter.config().setEncoding("utf-8");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Foot Locker");
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}