package com.fl.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static String reportPath;
    
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }
    
    private static ExtentReports createInstance() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport_" + timestamp + ".html";
        
        File reportDir = new File(System.getProperty("user.dir") + "/test-output");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }
        
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Foot Locker Automation Test Report");
        sparkReporter.config().setReportName("Selenium TestNG Automation Results");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Organization", "Foot Locker");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome Headless");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Selenium Version", "4.21.0");
        extent.setSystemInfo("TestNG Version", "7.10.2");
        
        return extent;
    }
    
    public static ExtentTest getTest() {
        return test.get();
    }
    
    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }
    
    public static void removeTest() {
        test.remove();
    }
    
    public static String getReportPath() {
        return reportPath;
    }
    
    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}