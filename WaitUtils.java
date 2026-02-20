package com.fl.automation.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {
    private static final int DEFAULT_TIMEOUT = 15;

    public static WebElement waitForPresence(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement waitForDisplayed(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static List<WebElement> waitForAllPresence(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static boolean waitForTextPresent(WebDriver driver, By locator, String text) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static boolean waitForInvisibility(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean waitForElementToBeClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(locator)) != null;
    }
}
