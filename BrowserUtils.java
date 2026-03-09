package com.fl.automation.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserUtils {

    public static void click(WebDriver driver, By locator) {
        WebElement element = WaitUtils.waitForElementClickable(driver, locator);
        element.click();
    }

    public static void sendKeys(WebDriver driver, By locator, String text) {
        WebElement element = WaitUtils.waitForElementVisible(driver, locator);
        element.clear();
        element.sendKeys(text);
    }

    public static String getText(WebDriver driver, By locator) {
        WebElement element = WaitUtils.waitForElementVisible(driver, locator);
        return element.getText();
    }

    public static boolean isDisplayed(WebDriver driver, By locator) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(driver, locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static void scrollIntoView(WebDriver driver, By locator) {
        WebElement element = WaitUtils.waitForElementPresent(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}