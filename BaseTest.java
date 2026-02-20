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
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected static Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        ConfigReader.loadConfig();
        String reportPath = ConfigReader.get("extent.report.path");
        String extentConfigPath = ConfigReader.get("extent.config.path");
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
            if (test.get() != null) {
                test.get().fail("Test failed. Screenshot: " + test.get().addScreenCaptureFromPath(screenshotPath));
            }
        }
        DriverFactory.quitDriver();
    }

    protected String takeScreenshot(WebDriver driver, String methodName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotDir = "screenshots";
        String screenshotPath = screenshotDir + "/" + methodName + "_" + timestamp + ".png";
        try {
            File dir = new File(screenshotDir);
            if (!dir.exists()) dir.mkdirs();
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), Paths.get(screenshotPath));
        } catch (IOException e) {
            logger.error("Failed to capture screenshot", e);
        }
        return screenshotPath;
    }

    protected ExtentTest createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        return extentTest;
    }
}
