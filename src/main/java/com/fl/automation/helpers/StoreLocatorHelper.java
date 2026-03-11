package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class StoreLocatorHelper {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By[] searchInputLocators = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private final By[] selectMyStoreLocators = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private final By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private final By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    private WebElement findElementWithFallback(By[] locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        throw new NoSuchElementException("Element not found with any of the provided locators");
    }

    private boolean isElementVisibleWithFallback(By[] locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }

    public boolean isLocationTextboxVisible() {
        return isElementVisibleWithFallback(searchInputLocators);
    }

    public boolean isSearchButtonVisible() {
        return isElementVisibleWithFallback(selectMyStoreLocators);
    }

    public void enterLocation(String location) {
        WebElement element = findElementWithFallback(searchInputLocators);
        element.clear();
        element.sendKeys(location);
    }

    public void clickSearchButton() {
        WebElement element = findElementWithFallback(selectMyStoreLocators);
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public boolean areSearchResultsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(storeResultCards)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
