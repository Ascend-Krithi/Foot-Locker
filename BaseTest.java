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
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseTest {
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        ConfigReader.loadConfig();
        String reportPath = ConfigReader.get("extentReportPath");
        ExtentSparkReporter spark = new ExtentSparkReporter(new File(reportPath));
        spark.loadXMLConfig(new File("src/main/resources/extent-config.xml"));
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        if (extent != null) extent.flush();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            String screenshotPath = takeScreenshot(driver, result.getName());
            if (test.get() != null) {
                test.get().fail(result.getThrowable());
                test.get().addScreenCaptureFromPath(screenshotPath);
            }
        }
        DriverFactory.quitDriver();
    }

    protected String takeScreenshot(WebDriver driver, String name) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String path = "screenshots/" + name + "_" + timestamp + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(path);
        dest.getParentFile().mkdirs();
        try {
            Files.copy(src.toPath(), dest.toPath());
        } catch (IOException e) {
            logger.error("Failed to save screenshot", e);
        }
        return path;
    }

    protected ExtentTest createTest(String testName) {
        ExtentTest t = extent.createTest(testName);
        test.set(t);
        return t;
    }
}
