package com.fl.automation.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseTest {
    protected static ExtentReports extent;
    protected ExtentTest test;
    protected WebDriver driver;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    public void setupExtent() throws IOException {
        if (extent == null) {
            String extentConfigPath = "src/main/resources/extent-config.xml";
            String reportPath = ConfigReader.get("extent.report.path");
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.loadXMLConfig(new File(extentConfigPath));
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void flushExtent() {
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
            String screenshotPath = takeScreenshot(result.getName());
            if (test != null && screenshotPath != null) {
                test.fail("Test Failed. Screenshot:").addScreenCaptureFromPath(screenshotPath);
            }
        }
        DriverFactory.quitDriver();
        logger.info("WebDriver quit");
    }

    private String takeScreenshot(String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String dest = "screenshots/" + testName + "_" + timestamp + ".png";
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(src.toPath(), Paths.get(dest));
            return dest;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot", e);
            return null;
        }
    }
}
