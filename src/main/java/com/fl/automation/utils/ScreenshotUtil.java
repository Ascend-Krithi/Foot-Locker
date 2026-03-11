package com.fl.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String destination = "target/screenshots/" + screenshotName + ".png";
            File finalDestination = new File(destination);
            FileUtils.copyFile(source, finalDestination);
            return destination;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}