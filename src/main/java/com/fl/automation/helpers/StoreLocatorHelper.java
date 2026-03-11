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

    private By[] selectMyStoreCandidates = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private By[] searchInputCandidates = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    private WebElement findElementWithFallback(By[] candidates) {
        for (By by : candidates) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } catch (TimeoutException ignored) {
            }
        }
        return null;
    }

    public boolean isLocationTextboxVisible() {
        try {
            WebElement element = findElementWithFallback(searchInputCandidates);
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonVisible() {
        try {
            By searchButton = By.cssSelector("button[type='submit']");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        WebElement element = findElementWithFallback(searchInputCandidates);
        if (element != null) {
            element.clear();
            element.sendKeys(location);
        }
    }

    public void clickSearchButton() {
        By searchButton = By.cssSelector("button[type='submit']");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(storeResultCards));
            return results != null && results.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStoreLocatorPopupVisible() {
        try {
            WebElement element = findElementWithFallback(searchInputCandidates);
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}