package com.fl.automation.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class BrowserUtils {
    public static void click(WebDriver driver, By locator) {
        WaitUtils.waitForElementClickable(driver, locator).click();
    }
    public static void type(WebDriver driver, By locator, String text) {
        WebElement el = WaitUtils.waitForElementVisible(driver, locator);
        el.clear();
        el.sendKeys(text);
    }
    public static boolean isDisplayed(WebDriver driver, By locator) {
        try {
            return WaitUtils.waitForElementVisible(driver, locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public static String getText(WebDriver driver, By locator) {
        return WaitUtils.waitForElementVisible(driver, locator).getText();
    }
    public static void scrollIntoView(WebDriver driver, By locator) {
        WebElement el = WaitUtils.waitForElementVisible(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
    }
}
