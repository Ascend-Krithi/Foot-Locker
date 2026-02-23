package com.fl.automation.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserUtils {
    public static void click(WebElement element, WebDriver driver) {
        WaitUtils.waitForClickable(driver, element).click();
    }

    public static void type(WebElement element, String text, WebDriver driver) {
        WaitUtils.waitForVisible(driver, element).clear();
        element.sendKeys(text);
    }

    public static boolean isDisplayed(WebElement element, WebDriver driver) {
        try {
            return WaitUtils.waitForVisible(driver, element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static String getText(WebElement element, WebDriver driver) {
        return WaitUtils.waitForVisible(driver, element).getText();
    }

    public static void scrollIntoView(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
