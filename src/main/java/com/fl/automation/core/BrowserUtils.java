package com.fl.automation.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserUtils {
    public static void click(WebDriver driver, By locator) {
        WaitUtils.waitForElementClickable(driver, locator).click();
    }

    public static void sendKeys(WebDriver driver, By locator, String text) {
        WebElement el = WaitUtils.waitForElementVisible(driver, locator);
        el.clear();
        el.sendKeys(text);
    }

    public static String getText(WebDriver driver, By locator) {
        return WaitUtils.waitForElementVisible(driver, locator).getText();
    }

    public static boolean isDisplayed(WebDriver driver, By locator) {
        try {
            return WaitUtils.waitForElementVisible(driver, locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
