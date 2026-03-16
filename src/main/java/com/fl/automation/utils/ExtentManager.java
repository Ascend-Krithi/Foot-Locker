package com.fl.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    
    private static ExtentReports extent;
    
    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance();
        }
        return extent;
    }
    
    private static ExtentReports createInstance() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Footlocker Automation Report");
        sparkReporter.config().setReportName("Store Locator Test Results");
        
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Footlocker Store Locator");
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("Framework", "Selenium TestNG");
        
        return extent;
    }
    
    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}