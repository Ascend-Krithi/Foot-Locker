package com.fl.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ExtentManager - Manages ExtentReports configuration and instances
 */
@Slf4j
public class ExtentManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final String REPORT_PATH = "test-output/ExtentReport.html";

    /**
     * Initializes ExtentReports instance
     */
    public static ExtentReports createInstance() {
        if (extent == null) {
            log.info("Initializing ExtentReports");
            
            // Create report directory if not exists
            File reportDir = new File("test-output");
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
            
            // Configure report
            sparkReporter.config().setDocumentTitle("Footlocker Automation Test Report");
            sparkReporter.config().setReportName("Selenium TestNG Automation Results");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
            sparkReporter.config().setEncoding("UTF-8");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            
            // System information
            extent.setSystemInfo("Application", "Footlocker Store Locator");
            extent.setSystemInfo("Environment", "Production");
            extent.setSystemInfo("User", System.getProperty("user.name"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Browser", "Chrome (Headless)");
            
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            extent.setSystemInfo("Execution Time", timestamp);
            
            log.info("ExtentReports initialized successfully at: {}", REPORT_PATH);
        }
        return extent;
    }

    /**
     * Gets the ExtentReports instance
     */
    public static ExtentReports getExtent() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    /**
     * Creates a new test in the report
     */
    public static ExtentTest createTest(String testName, String description) {
        ExtentTest test = getExtent().createTest(testName, description);
        extentTest.set(test);
        log.info("Created ExtentTest: {}", testName);
        return test;
    }

    /**
     * Gets the current thread's ExtentTest instance
     */
    public static ExtentTest getTest() {
        return extentTest.get();
    }

    /**
     * Removes the current thread's ExtentTest instance
     */
    public static void removeTest() {
        extentTest.remove();
    }

    /**
     * Flushes the report (writes to file)
     */
    public static void flush() {
        if (extent != null) {
            log.info("Flushing ExtentReports");
            extent.flush();
        }
    }
}