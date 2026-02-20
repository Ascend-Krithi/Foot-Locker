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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
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
    protected Logger logger;

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        String extentConfigPath = "src/main/resources/extent-config.xml";
        String reportPath = ConfigReader.get("extent.report.path");
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        try {
            spark.loadXMLConfig(new FileInputStream(extentConfigPath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load extent-config.xml", e);
        }
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(java.lang.reflect.Method method) {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        logger = LogManager.getLogger(method.getDeclaringClass());
        test = extent.createTest(method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
            test.fail(result.getThrowable());
            if (screenshotPath != null) {
                test.addScreenCaptureFromPath(screenshotPath);
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test skipped");
        }
        DriverFactory.quitDriver();
    }

    private String takeScreenshot(String methodName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String dest = "screenshots/" + methodName + "_" + timestamp + ".png";
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(src.toPath(), Paths.get(dest));
            return dest;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot", e);
            return null;
        }
    }
}
