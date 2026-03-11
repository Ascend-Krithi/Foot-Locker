package com.fl.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String destination = "test-output/screenshots/" + fileName;

        try {
            File screenshotDir = new File("test-output/screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(destination);
            FileHandler.copy(srcFile, destFile);
            return destination;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
