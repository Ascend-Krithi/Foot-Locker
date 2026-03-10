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

    private By[] findStoreHeaderLocators = {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    private By[] selectMyStoreLocators = {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private By[] searchInputLocators = {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private By storeResultCardsLocator = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddressLocator = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButtonLocator = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsLocator = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void clickFindAStore() {
        WebElement element = waitForFirstVisible(findStoreHeaderLocators);
        clickWithFallback(element);
    }

    public boolean isSelectMyStoreVisible() {
        try {
            WebElement element = waitForFirstVisible(selectMyStoreLocators);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        WebElement element = waitForFirstVisible(selectMyStoreLocators);
        clickWithFallback(element);
    }

    public boolean isSearchInputVisible() {
        try {
            WebElement element = waitForFirstVisible(searchInputLocators);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        WebElement element = waitForFirstVisible(searchInputLocators);
        element.clear();
        element.sendKeys(location);
    }

    public boolean areStoreResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResultCardsLocator));
            List<WebElement> results = driver.findElements(storeResultCardsLocator);
            return results.size() > 0;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickSearchForStores() {
        By[] searchButtonLocators = {
            By.xpath("//button[contains(.,'Search for Stores')]"),
            By.cssSelector("button[type='submit']"),
            By.xpath("//button[contains(@class,'search')]"),
            By.xpath("//input[@type='submit']"),
            By.xpath("//button[contains(.,'Search')]")
        };
        WebElement element = waitForFirstVisible(searchButtonLocators);
        clickWithFallback(element);
    }

    private WebElement waitForFirstVisible(By[] locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (TimeoutException e) {
                continue;
            }
        }
        throw new RuntimeException("None of the fallback locators found element");
    }

    private void clickWithFallback(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
