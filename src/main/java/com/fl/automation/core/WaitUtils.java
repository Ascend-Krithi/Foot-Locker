package com.fl.automation.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
    private static int timeout = Integer.parseInt(ConfigReader.get("timeout"));

    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
