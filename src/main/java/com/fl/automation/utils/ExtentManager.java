package com.fl.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    
    private static ExtentReports extent;
    
    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Foot Locker Automation Report");
            spark.config().setReportName("Test Execution Report");
            
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Application", "Foot Locker");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("User", "QA Automation Team");
        }
        return extent;
    }
}
