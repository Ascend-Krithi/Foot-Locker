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

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void clickSelectMyStore() {
        By[] candidates = new By[]{
            By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
            By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
        };

        WebElement target = null;
        for (By by : candidates) {
            try {
                target = wait.until(ExpectedConditions.elementToBeClickable(by));
                break;
            } catch (TimeoutException ignored) {
            }
        }

        if (target == null) {
            throw new TimeoutException("Could not locate 'Select My Store' control using known locators.");
        }

        try {
            target.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", target);
        }
    }

    public void enterLocationAndSearch(String location) {
        By[] searchInputCandidates = new By[]{
            By.cssSelector("input[type='search']"),
            By.cssSelector("input[name='q']"),
            By.cssSelector("input[aria-label*='Search']"),
            By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
        };

        WebElement searchInput = null;
        for (By by : searchInputCandidates) {
            try {
                searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                break;
            } catch (TimeoutException ignored) {
            }
        }

        if (searchInput == null) {
            throw new TimeoutException("Could not locate search input using known locators.");
        }

        searchInput.clear();
        searchInput.sendKeys(location);
    }

    public void clickSearchForStoresButton() {
        By searchButtonBy = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search') or contains(.,'Find Stores')]");
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchButtonBy));

        try {
            searchButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchButton);
        }
    }

    public boolean areStoreResultsDisplayed() {
        By storeResultsBy = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
        try {
            List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(storeResultsBy));
            return results.size() > 0;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isStoreLocatorPopupOpen() {
        By popupBy = By.cssSelector("[role='dialog'], .modal, [class*='popup'], [class*='store-locator']");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(popupBy));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isLocationTextboxPresent() {
        By[] searchInputCandidates = new By[]{
            By.cssSelector("input[type='search']"),
            By.cssSelector("input[name='q']"),
            By.cssSelector("input[aria-label*='Search']"),
            By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
        };

        for (By by : searchInputCandidates) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(by));
                return true;
            } catch (TimeoutException ignored) {
            }
        }
        return false;
    }

    public boolean isSearchForStoresButtonPresent() {
        By searchButtonBy = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search') or contains(.,'Find Stores')]");
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(searchButtonBy));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}