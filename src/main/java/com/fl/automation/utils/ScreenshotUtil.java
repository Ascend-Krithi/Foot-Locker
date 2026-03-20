package com.fl.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";
        String directory = System.getProperty("user.dir") + "/test-output/screenshots/";
        
        File screenshotDir = new File(directory);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
        
        String filePath = directory + fileName;
        
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return filePath;
    }
    
    public static WebDriver getDriver(Object testClass) {
        try {
            Field driverField = testClass.getClass().getSuperclass().getDeclaredField("driver");
            driverField.setAccessible(true);
            return (WebDriver) driverField.get(testClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}