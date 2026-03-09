package com.fl.automation.utils;

import org.openqa.selenium.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String name){

        try{

            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            String path = "test-output/screenshots/" + name + ".png";

            File dest = new File(path);
            dest.getParentFile().mkdirs();

            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

            return path;

        }catch(Exception e){
            return null;
        }
    }
}