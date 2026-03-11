package com.fl.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String dest = System.getProperty("user.dir") + "/test-output/screenshots/" + screenshotName + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
}
