package com.fl.automation.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserUtils {
    public static void click(WebDriver driver, WebElement element) {
        WaitUtils.waitForClickable(driver, element).click();
    }

    public static void type(WebDriver driver, WebElement element, String text) {
        WaitUtils.waitForVisible(driver, element).clear();
        element.sendKeys(text);
    }

    public static boolean isDisplayed(WebDriver driver, WebElement element) {
        try {
            return WaitUtils.waitForVisible(driver, element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static String getText(WebDriver driver, WebElement element) {
        return WaitUtils.waitForVisible(driver, element).getText();
    }

    public static void scrollIntoView(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
