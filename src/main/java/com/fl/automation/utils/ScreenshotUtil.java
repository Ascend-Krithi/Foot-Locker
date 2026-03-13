package com.fl.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";
        String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots/" + fileName;
        
        File screenshotDir = new File(System.getProperty("user.dir") + "/test-output/screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
        
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);
            FileUtils.copyFile(source, destination);
            return screenshotPath;
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
    
    public static String captureScreenshotBase64(WebDriver driver) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            return ts.getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            System.err.println("Failed to capture base64 screenshot: " + e.getMessage());
            return null;
        }
    }
}