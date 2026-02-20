package com.fl.automation.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fl.automation.utils.ConfigReader;
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
import java.util.Properties;

public abstract class BaseTest {
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        String extentConfigPath = ConfigReader.getProperty("extent.report.path");
        ExtentSparkReporter spark = new ExtentSparkReporter(extentConfigPath);
        spark.loadXMLConfig(new FileInputStream(new File("src/main/resources/extent-config.xml")));
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
    public void setupTest(Method method) {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        test.set(extent.createTest(method.getDeclaringClass().getSimpleName() + "::" + method.getName()));
        logger.info("Starting test: " + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = takeScreenshot(result.getName());
            test.get().fail(result.getThrowable());
            if (screenshotPath != null) {
                test.get().addScreenCaptureFromPath(screenshotPath);
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.get().pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.get().skip("Test skipped");
        }
        DriverFactory.quitDriver();
        logger.info("Finished test: " + result.getName());
    }

    private String takeScreenshot(String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String dest = "reports/screenshots/" + testName + System.currentTimeMillis() + ".png";
            Files.createDirectories(Paths.get("reports/screenshots/"));
            Files.copy(src.toPath(), Paths.get(dest));
            return dest;
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
