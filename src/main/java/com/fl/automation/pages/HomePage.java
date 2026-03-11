package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait waitShort;
    private final WebDriverWait wait;

    private final By[] findStoreLocators = new By[] {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    private final By[] selectMyStoreLocators = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private final By[] searchInputLocators = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private final By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");

    private final By storeAddressInsideCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.waitShort = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void clickFindStore() {
        WebElement findStoreLink = findElementWithFallback(findStoreLocators);
        clickWithJsFallback(findStoreLink);
    }

    public void clickSelectMyStore() {
        WebElement selectMyStoreBtn = findElementWithFallback(selectMyStoreLocators);
        clickWithJsFallback(selectMyStoreBtn);
    }

    public void enterLocation(String location) {
        WebElement searchInput = findElementWithFallback(searchInputLocators);
        searchInput.clear();
        searchInput.sendKeys(location);
    }

    public void clickSearchForStores() {
        By searchButtonLocator = By.xpath("//button[contains(.,'Search') or contains(.,'Find Stores')]");
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
        clickWithJsFallback(searchBtn);
    }

    public boolean areStoreResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(storeResultCards));
            return driver.findElements(storeResultCards).size() > 0;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean storeResultsContainLocation(String expectedLocation) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(storeResultCards));
            return driver.findElements(storeAddressInsideCard).stream()
                    .anyMatch(el -> el.getText().toLowerCase().contains(expectedLocation.toLowerCase()));
        } catch (TimeoutException e) {
            return false;
        }
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