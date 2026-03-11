package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    private List<By> locationTextboxLocators = Arrays.asList(
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    );

    private List<By> searchButtonLocators = Arrays.asList(
        By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]"),
        By.cssSelector("[aria-label*='Search for store' i]"),
        By.cssSelector("button[type='submit']"),
        By.cssSelector("button[aria-label*='Search' i]"),
        By.xpath("//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search')]")
    );

    private List<By> storeResultsLocators = Arrays.asList(
        By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']")
    );

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public boolean isLocationTextboxPresent() {
        try {
            WebElement element = waitForAnyElement(locationTextboxLocators);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonPresent() {
        try {
            WebElement element = waitForAnyElement(searchButtonLocators);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        WebElement element = waitForAnyElement(locationTextboxLocators);
        element.clear();
        element.sendKeys(location);
    }

    public void clickSearchButton() {
        WebElement element = waitForAnyElement(searchButtonLocators);
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public boolean areStoreResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(storeResultsLocators.get(0)));
            List<WebElement> results = driver.findElements(storeResultsLocators.get(0));
            return results.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement waitForAnyElement(List<By> locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception ignored) {
            }
        }
        throw new RuntimeException("None of the fallback locators found the element");
    }
}