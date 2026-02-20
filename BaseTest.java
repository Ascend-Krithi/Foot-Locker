package com.fl.automation.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fl.automation.core.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter(ConfigReader.get("extent.report.path"));
            spark.loadXMLConfig(new File("src/main/resources/extent-config.xml"));
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
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
            String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
            test.get().fail(result.getThrowable());
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
        DriverFactory.quitDriver();
    }

    public String takeScreenshot(String methodName) {
        WebDriver driver = DriverFactory.getDriver();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotDir = "screenshots/";
        String screenshotPath = screenshotDir + methodName + "_" + timestamp + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File dest = new File(screenshotPath);
            dest.getParentFile().mkdirs();
            Files.copy(src.toPath(), dest.toPath());
            return screenshotPath;
        } catch (IOException e) {
            logger.error("Failed to save screenshot", e);
            return null;
        }
    }
}
