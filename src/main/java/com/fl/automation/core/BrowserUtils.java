package com.fl.automation.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BrowserUtils {

    public static void click(WebDriver driver, By locator) {
        WebElement element = WaitUtils.waitForElementToBeClickable(driver, locator);
        element.click();
    }

    public static void type(WebDriver driver, By locator, String text) {
        WebElement element = WaitUtils.waitForElementToBeVisible(driver, locator);
        element.clear();
        element.sendKeys(text);
    }

    public static boolean isDisplayed(WebDriver driver, By locator) {
        try {
            WebElement element = WaitUtils.waitForElementToBeVisible(driver, locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static String getText(WebDriver driver, By locator) {
        WebElement element = WaitUtils.waitForElementToBeVisible(driver, locator);
        return element.getText();
    }

    public static void scrollIntoView(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static List<WebElement> findElements(WebDriver driver, By locator) {
        return driver.findElements(locator);
    }
}