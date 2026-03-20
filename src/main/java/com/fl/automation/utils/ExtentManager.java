package com.fl.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    private static final String REPORT_PATH = "test-output/ExtentReport.html";

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static ExtentReports createInstance() {
        File reportDir = new File("test-output");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Foot Locker Automation Report");
        sparkReporter.config().setReportName("Test Execution Report");
        sparkReporter.config().setEncoding("utf-8");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Foot Locker");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Java Version", "17");

        return extent;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}