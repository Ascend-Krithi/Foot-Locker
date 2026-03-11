package com.fl.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtil - Utility for capturing screenshots
 */
@Slf4j
public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "test-output/screenshots";

    /**
     * Captures screenshot and saves to file
     * @param driver WebDriver instance
     * @param testName Name of the test
     * @return Path to screenshot file
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            // Create screenshots directory if not exists
            File screenshotDir = new File(SCREENSHOT_DIR);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Generate filename with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + File.separator + fileName;

            // Capture screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            
            // Copy to destination
            Path destination = Paths.get(filePath);
            Files.copy(srcFile.toPath(), destination);
            
            log.info("Screenshot captured: {}", filePath);
            return filePath;
            
        } catch (IOException e) {
            log.error("Failed to capture screenshot: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Captures screenshot and returns as base64 string for embedding in reports
     * @param driver WebDriver instance
     * @return Base64 encoded screenshot
     */
    public static String captureScreenshotAsBase64(WebDriver driver) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            String base64Screenshot = screenshot.getScreenshotAs(OutputType.BASE64);
            log.info("Screenshot captured as Base64");
            return base64Screenshot;
        } catch (Exception e) {
            log.error("Failed to capture screenshot as Base64: {}", e.getMessage());
            return null;
        }
    }
}