package com.fl.automation.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseTest {
    protected static ExtentReports extent;
    protected ExtentTest test;
    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger(this.getClass());

    @BeforeSuite(alwaysRun = true)
    public void setupExtent() {
        String extentConfigPath = "src/main/resources/extent-config.xml";
        String reportPath = ConfigReader.get("extent.report.path");
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        try {
            spark.loadXMLConfig(new File(extentConfigPath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load extent-config.xml", e);
        }
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownExtent() {
        if (extent != null) {
            extent.flush();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        logger.info("WebDriver initialized");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
            if (test != null && screenshotPath != null) {
                test.fail("Test failed. Screenshot:").addScreenCaptureFromPath(screenshotPath);
            }
        }
        DriverFactory.quitDriver();
        logger.info("WebDriver quit");
    }

    private String takeScreenshot(String methodName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String dest = "screenshots/" + methodName + "_" + System.currentTimeMillis() + ".png";
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(src.toPath(), Paths.get(dest));
            return dest;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot", e);
            return null;
        }
    }
}
