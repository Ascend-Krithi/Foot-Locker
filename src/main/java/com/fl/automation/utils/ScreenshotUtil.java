package com.fl.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String dest = "test-output/screenshots/" + screenshotName + "_" + System.currentTimeMillis() + ".png";
        try {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File(dest);
            destination.getParentFile().mkdirs();
            FileHandler.copy(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
}