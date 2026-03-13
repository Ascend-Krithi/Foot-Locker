package com.fl.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = screenshotName + "_" + timestamp + ".png";
            String directory = "test-output/screenshots/";
            
            File screenshotDir = new File(directory);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
            
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String destination = directory + fileName;
            File finalDestination = new File(destination);
            FileHandler.copy(source, finalDestination);
            
            return destination;
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}