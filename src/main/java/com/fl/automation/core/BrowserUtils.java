package com.fl.automation.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class BrowserUtils {
    public static void click(WebDriver driver, By locator) {
        driver.findElement(locator).click();
    }

    public static void type(WebDriver driver, By locator, String text) {
        WebElement el = driver.findElement(locator);
        el.clear();
        el.sendKeys(text);
    }

    public static boolean isDisplayed(WebDriver driver, By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static String getText(WebDriver driver, By locator) {
        return driver.findElement(locator).getText();
    }

    public static void scrollIntoView(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
