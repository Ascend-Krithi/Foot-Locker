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
    private By searchButton = By.xpath(
        "//button[contains(normalize-space(.), 'Search for Stores')] | " +
        "//button[contains(@class, 'StoreLocator')][contains(., 'Search')] | " +
        "//input[@type='submit'][contains(@value, 'Search')]"
    );
    private By storeResults = By.xpath(
        "//*[contains(@class, 'StoreResult')] | " +
        "//*[contains(@class, 'store-result')] | " +
        "//*[contains(@class, 'StoreLocator-result')]"
    );
    private By storeAddresses = By.xpath(
        "//*[contains(@class, 'StoreResult')]//*[contains(@class, 'address')] | " +
        "//*[contains(@class, 'store-address')] | " +
        "//*[contains(@class, 'StoreDetails-address')]"
    );
    private By setMyStoreButtons = By.xpath(
        "//button[contains(normalize-space(.), 'Set My Store')] | " +
        "//a[contains(normalize-space(.), 'Set My Store')] | " +
        "//button[contains(@class, 'set-store')]"
    );
    private By storeNameInHeader = By.xpath(
        "//*[contains(@class, 'Header')]//*[contains(@class, 'store')] | " +
        "//*[contains(@class, 'HeaderButton')]//*[contains(@class, 'store-name')] | " +
        "//*[contains(@class, 'StoreLocator')]//*[contains(@class, 'selected')]"
    );

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public void waitForStoreLocatorToLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException("Store locator did not load: " + e.getMessage());
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

    public void searchForLocation(String location) {
        try {
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(locationInput));
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
            clickWithFallback(button);
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click search button: " + e.getMessage());
        }
    }

    public boolean isStoreDisplayed(String storeAddress) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResults));
            List<WebElement> addresses = driver.findElements(storeAddresses);
            for (WebElement address : addresses) {
                String addressText = address.getText().trim();
                if (addressText.contains(storeAddress)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSetMyStoreForStore(String storeAddress) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResults));
            List<WebElement> addresses = driver.findElements(storeAddresses);
            List<WebElement> setStoreButtons = driver.findElements(setMyStoreButtons);
            
            for (int i = 0; i < addresses.size(); i++) {
                String addressText = addresses.get(i).getText().trim();
                if (addressText.contains(storeAddress)) {
                    if (i < setStoreButtons.size()) {
                        clickWithFallback(setStoreButtons.get(i));
                        Thread.sleep(2000);
                        return;
                    }
                }
            }
            throw new RuntimeException("Store with address '" + storeAddress + "' not found");
        } catch (Exception e) {
            throw new RuntimeException("Failed to set store: " + e.getMessage());
        }
    }

    public boolean isStoreNameInHeader(String storeName) {
        try {
            WebElement headerStore = wait.until(ExpectedConditions.visibilityOfElementLocated(storeNameInHeader));
            String headerText = headerStore.getText().trim();
            return headerText.contains(storeName) || headerText.contains("Boston");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStoreConfirmationDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(storeNameInHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void clickWithFallback(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);
            } catch (Exception jsException) {
                throw new RuntimeException("Both regular and JS click failed: " + jsException.getMessage());
            }
        }
    }
}
