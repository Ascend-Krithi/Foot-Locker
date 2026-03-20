package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {
    private WebDriver driver;
    private WebDriverWait wait;

    private By locationInput = By.id("StoreLocator_search_query");
    private By searchButton = By.xpath("//button[contains(text(),'Search for Stores')]");
    private By storeResultsContainer = By.xpath("//*[contains(@class,'store-card') or contains(@class,'StoreCard') or contains(@class,'store-details') or contains(@class,'StoreDetails') or contains(@class,'store-result') or contains(@class,'StoreResult')]");
    private By storeNameHeader = By.xpath("//*[contains(@class,'header')]//*[contains(text(),'Washington')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void waitForStoreLocatorToLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
            Thread.sleep(500);
        } catch (Exception e) {
            // Continue if already loaded
        }
    }

    public boolean isLocationSearchInputDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
            input.clear();
            input.sendKeys(location);
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter location: " + e.getMessage());
        }
    }

    public void clickSearchButton() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            button.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            WebElement button = driver.findElement(searchButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    public void waitForStoreResults() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResultsContainer));
            Thread.sleep(1000);
        } catch (Exception e) {
            // Continue if results already visible
        }
    }

    public boolean isStoreDisplayed(String address) {
        try {
            waitForStoreResults();
            By storeLocator = By.xpath("//*[contains(text(),'" + address + "')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(storeLocator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSetMyStoreForStore(String address) {
        try {
            By setStoreButton = By.xpath("//*[contains(text(),'" + address + "')]/ancestor::*[contains(@class,'store-card') or contains(@class,'StoreCard')]//*[contains(text(),'Set') or contains(text(),'Update')]");
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(setStoreButton));
            button.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            By setStoreButton = By.xpath("//*[contains(text(),'" + address + "')]/ancestor::*[contains(@class,'store-card') or contains(@class,'StoreCard')]//*[contains(text(),'Set') or contains(text(),'Update')]");
            WebElement button = driver.findElement(setStoreButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    public boolean isStoreConfirmationDisplayed() {
        try {
            By confirmationLocator = By.xpath("//*[contains(text(),'selected') or contains(text(),'preferred') or contains(text(),'My Store')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationLocator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStoreNameInHeader(String storeName) {
        try {
            By headerStoreLocator = By.xpath("//*[contains(@class,'header')]//*[contains(text(),'" + storeName + "')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(headerStoreLocator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}