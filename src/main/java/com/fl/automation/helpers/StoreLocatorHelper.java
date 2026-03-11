package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverWait shortWait;

    private By[] selectMyStoreCandidates = new By[]{
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private By[] locationInputCandidates = new By[]{
        By.cssSelector("input[placeholder*='Enter address' i], input[placeholder*='city' i], input[placeholder*='ZIP' i]"),
        By.cssSelector("input[aria-label*='Location' i], input[type='search']"),
        By.cssSelector("input[name*='location' i], input[id*='location' i]")
    };

    private By[] searchButtonCandidates = new By[]{
        By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]"),
        By.cssSelector("[aria-label*='Search for store' i], button[type='submit']"),
        By.cssSelector("button[aria-label*='Search' i]")
    };

    private By[] storeResultsCandidates = new By[]{
        By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']")
    };

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }

    public boolean isSelectMyStoreVisible() {
        return findElementWithFallback(selectMyStoreCandidates) != null;
    }

    public void clickSelectMyStore() {
        WebElement element = findElementWithFallback(selectMyStoreCandidates);
        if (element != null) {
            clickWithJsFallback(element);
        } else {
            throw new RuntimeException("Select My Store element not found");
        }
    }

    public boolean isLocationInputVisible() {
        return findElementWithFallback(locationInputCandidates) != null;
    }

    public boolean isSearchButtonVisible() {
        return findElementWithFallback(searchButtonCandidates) != null;
    }

    public void enterLocation(String location) {
        WebElement element = findElementWithFallback(locationInputCandidates);
        if (element != null) {
            element.clear();
            element.sendKeys(location);
        } else {
            throw new RuntimeException("Location input not found");
        }
    }

    public void clickSearchButton() {
        WebElement element = findElementWithFallback(searchButtonCandidates);
        if (element != null) {
            clickWithJsFallback(element);
        } else {
            throw new RuntimeException("Search button not found");
        }
    }

    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(storeResultsCandidates[0]));
            return results != null && results.size() > 0;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private WebElement findElementWithFallback(By[] candidates) {
        for (By locator : candidates) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (TimeoutException e) {
                continue;
            }
        }
        return null;
    }

    private void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}