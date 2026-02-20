package com.fl.automation.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class WaitUtils {
    private static int getExplicitWait() {
        String wait = ConfigReader.get("explicitWait");
        return wait != null ? Integer.parseInt(wait) : 20;
    }

    public static WebElement waitForPresence(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(getExplicitWait()))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement waitForDisplayed(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(getExplicitWait()))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static List<WebElement> waitForAllPresence(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(getExplicitWait()))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static boolean waitForTextPresent(WebDriver driver, By locator, String text) {
        return new WebDriverWait(driver, Duration.ofSeconds(getExplicitWait()))
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static boolean waitForElementClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(getExplicitWait()))
                .until(ExpectedConditions.elementToBeClickable(locator)) != null;
    }
}
