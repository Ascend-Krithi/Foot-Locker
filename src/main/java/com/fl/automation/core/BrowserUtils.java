package com.fl.automation.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class BrowserUtils {
    public static void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            WebDriver driver = DriverFactory.getDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

    public static void scrollToElement(WebElement element) {
        WebDriver driver = DriverFactory.getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
