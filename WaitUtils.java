package com.fl.automation.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {
    private static int getExplicitWait() {
        String wait = ConfigReader.get("explicitWait");
        return wait != null ? Integer.parseInt(wait) : 20;
    }

    public static WebElement waitForElementDisplayed(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(getExplicitWait()))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementPresent(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(getExplicitWait()))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static List<WebElement> waitForElementsPresent(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(getExplicitWait()))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static boolean waitForTextPresent(WebDriver driver, By locator, String text) {
        return new WebDriverWait(driver, Duration.ofSeconds(getExplicitWait()))
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
}
