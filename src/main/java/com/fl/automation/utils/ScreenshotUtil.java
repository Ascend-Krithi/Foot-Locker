package com.fl.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String testName) {
        String dir = "test-output/screenshots/";
        new File(dir).mkdirs();
        String fileName = dir + testName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(src.toPath(), Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
