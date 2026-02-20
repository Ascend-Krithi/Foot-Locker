package com.fl.automation.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fl.automation.core.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseTest {
    protected static ExtentReports extent;
    protected ExtentTest test;
    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger(this.getClass());

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        ConfigReader.loadConfig();
        String reportPath = ConfigReader.get("extent.report.path");
        ExtentSparkReporter spark = new ExtentSparkReporter(new File(reportPath));
        spark.loadXMLConfig(new File("src/main/resources/extent-config.xml"));
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        if (extent != null) {
            extent.flush();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        logger.info("Driver initialized");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && test != null) {
            String screenshotPath = takeScreenshot(result.getName());
            test.fail(result.getThrowable());
            if (screenshotPath != null) {
                test.addScreenCaptureFromPath(screenshotPath);
            }
        }
        DriverFactory.quitDriver();
        logger.info("Driver quit");
    }

    private String takeScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String dest = "screenshots/" + name + "_" + timestamp + ".png";
            File destFile = new File(dest);
            destFile.getParentFile().mkdirs();
            Files.copy(src.toPath(), destFile.toPath());
            return dest;
        } catch (IOException e) {
            logger.error("Screenshot capture failed", e);
            return null;
        }
    }
}
