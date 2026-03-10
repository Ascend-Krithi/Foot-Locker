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
            
            File screenshotDir = new File("test-output/screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotDir, fileName);
            FileHandler.copy(source, destination);
            
            return destination.getAbsolutePath();
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
