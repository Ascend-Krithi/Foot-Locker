package com.fl.automation.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fl.automation.core.DriverFactory;
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
import java.nio.file.Paths;

public abstract class BaseTest {
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        String extentConfigPath = ConfigReader.get("extent.config.path");
        String reportPath = ConfigReader.get("extent.report.path");
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.loadXMLConfig(new File(extentConfigPath));
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
    public void setup() {
        DriverFactory.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            String screenshotPath = takeScreenshot(driver, result.getMethod().getMethodName());
            if (test.get() != null && screenshotPath != null) {
                test.get().fail("Test Failed. Screenshot:").addScreenCaptureFromPath(screenshotPath);
            }
        }
        DriverFactory.quitDriver();
    }

    protected String takeScreenshot(WebDriver driver, String methodName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String dest = "screenshots/" + methodName + "_" + System.currentTimeMillis() + ".png";
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(src.toPath(), Paths.get(dest));
            return dest;
        } catch (IOException e) {
            logger.error("Failed to capture screenshot", e);
            return null;
        }
    }

    protected void startTest(String testName) {
        test.set(extent.createTest(testName));
    }
}
