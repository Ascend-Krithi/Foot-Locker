package com.fl.automation.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserUtils {
    public static void click(WebElement element) {
        element.click();
    }
    public static void type(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
    public static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public static String getText(WebElement element) {
        return element.getText();
    }
    public static void scrollIntoView(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
