package com.fl.automation.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
    private static final int TIMEOUT = 5;

    public static WebElement waitForElementVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static boolean waitForElementDisplayed(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean waitForElementClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}