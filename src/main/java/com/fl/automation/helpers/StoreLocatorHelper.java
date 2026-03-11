package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreLocatorHelper {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By[] locationInputLocators = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private final By[] searchButtonLocators = new By[] {
        By.xpath("//button[contains(.,'Search for Stores')]"),
        By.xpath("//button[contains(.,'Search')]"),
        By.xpath("//button[contains(.,'Find Stores')]"),
        By.cssSelector("button[type='submit']")
    };

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void enterLocation(String location) {
        WebElement locationInput = findElementWithFallback(locationInputLocators);
        locationInput.clear();
        locationInput.sendKeys(location);
    }

    public void clickSearchForStores() {
        WebElement searchBtn = findElementWithFallback(searchButtonLocators);
        clickWithJsFallback(searchBtn);
    }

    private WebElement findElementWithFallback(By[] locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (TimeoutException ignored) {
            }
        }
        throw new NoSuchElementException("None of the fallback locators found an element.");
    }

    private void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}