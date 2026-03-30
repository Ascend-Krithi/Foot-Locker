package com.fl.automation.helpers;

import org.openqa.selenium.By;
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
    private By searchButton = By.cssSelector("button[data-testid='store-locator-search-button']");
    private By storeResultsContainer = By.cssSelector("div[class*='store-card'] div[class*='StoreCard'] div[class*='store-details'] div[class*='StoreDetails']");
    private By setMyStoreButton = By.cssSelector("button[data-testid='set-my-store-button']");
    private By storeConfirmation = By.cssSelector("div[data-testid='store-confirmation']");
    private By storeNameHeader = By.cssSelector("span[data-testid='store-name-header']");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void waitForStoreLocatorSearchInput() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
    }

    public void clearLocationInput() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
        input.clear();
    }

    public void searchForLocation(String location) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
        input.clear();
        input.sendKeys(location);
    }

    public void clickSearchButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        button.click();
    }

    public void waitForStoreResults() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(storeResultsContainer));
    }

    public boolean isStoreDisplayed(String address) {
        try {
            waitForStoreResults();
            List<WebElement> storeElements = driver.findElements(storeResultsContainer);
            for (WebElement store : storeElements) {
                if (store.getText().contains(address)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areStoreResultsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(storeResultsContainer)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSetMyStoreForStore() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(setMyStoreButton));
        button.click();
    }

    public boolean isStoreConfirmationDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(storeConfirmation)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStoreNameInHeader() {
        try {
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(storeNameHeader));
            return header.isDisplayed() && header.getText().contains("Washington");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLocationSearchInputDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForStoreLocatorToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
    }
}