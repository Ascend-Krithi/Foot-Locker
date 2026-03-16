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
    
    private static final By[] FIND_STORE_LOCATORS = {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store')]")
    };
    
    private static final By[] SELECT_MY_STORE_LOCATORS = {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store')]"),
        By.xpath("//button[contains(.,'Select My Store')]")
    };
    
    private static final By[] LOCATION_INPUT_LOCATORS = {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']")
    };
    
    private static final By[] SEARCH_BUTTON_LOCATORS = {
        By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]"),
        By.cssSelector("[aria-label*='Search for store' i]")
    };
    
    private static final By[] STORE_RESULTS_LOCATORS = {
        By.cssSelector("[data-qa='location']"),
        By.cssSelector(".c-location-card"),
        By.cssSelector(".location")
    };
    
    private static final By[] STORE_ADDRESS_LOCATORS = {
        By.cssSelector("[data-qa='address']"),
        By.cssSelector(".c-address"),
        By.cssSelector("address")
    };
    
    private static final By[] SET_MY_STORE_BUTTON_LOCATORS = {
        By.xpath(".//button[contains(.,'Set My Store') or contains(.,'Make This My Store')]")
    };
    
    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }
    
    private WebElement findElementWithFallback(By[] locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }
    
    private List<WebElement> findElementsWithFallback(By[] locators) {
        for (By locator : locators) {
            try {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    return elements;
                }
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Elements not found with any of the provided locators");
    }
    
    public void clickFindAStore() {
        WebElement findStoreLink = findElementWithFallback(FIND_STORE_LOCATORS);
        wait.until(ExpectedConditions.elementToBeClickable(findStoreLink)).click();
    }
    
    public boolean isFindAStorePopupDisplayed() {
        try {
            findElementWithFallback(SELECT_MY_STORE_LOCATORS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSelectMyStoreLinkPresent() {
        try {
            WebElement selectMyStore = findElementWithFallback(SELECT_MY_STORE_LOCATORS);
            return selectMyStore.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickSelectMyStore() {
        WebElement selectMyStore = findElementWithFallback(SELECT_MY_STORE_LOCATORS);
        wait.until(ExpectedConditions.elementToBeClickable(selectMyStore)).click();
    }
    
    public boolean isLocationTextboxPresent() {
        try {
            WebElement locationInput = findElementWithFallback(LOCATION_INPUT_LOCATORS);
            return locationInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSearchButtonPresent() {
        try {
            WebElement searchButton = findElementWithFallback(SEARCH_BUTTON_LOCATORS);
            return searchButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void enterLocation(String location) {
        WebElement locationInput = findElementWithFallback(LOCATION_INPUT_LOCATORS);
        wait.until(ExpectedConditions.elementToBeClickable(locationInput));
        locationInput.clear();
        locationInput.sendKeys(location);
    }
    
    public void clickSearchForStores() {
        WebElement searchButton = findElementWithFallback(SEARCH_BUTTON_LOCATORS);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }
    
    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> results = findElementsWithFallback(STORE_RESULTS_LOCATORS);
            return !results.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSpecificStoreAddressVisible(String expectedAddress) {
        try {
            List<WebElement> storeResults = findElementsWithFallback(STORE_RESULTS_LOCATORS);
            for (WebElement store : storeResults) {
                try {
                    WebElement addressElement = store.findElement(STORE_ADDRESS_LOCATORS[0]);
                    String actualAddress = addressElement.getText().trim();
                    if (actualAddress.contains(expectedAddress) || expectedAddress.contains(actualAddress)) {
                        return true;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void setStoreAsPreferred(String storeAddress) {
        try {
            List<WebElement> storeResults = findElementsWithFallback(STORE_RESULTS_LOCATORS);
            for (WebElement store : storeResults) {
                try {
                    WebElement addressElement = store.findElement(STORE_ADDRESS_LOCATORS[0]);
                    String actualAddress = addressElement.getText().trim();
                    if (actualAddress.contains(storeAddress) || storeAddress.contains(actualAddress)) {
                        WebElement setMyStoreButton = store.findElement(SET_MY_STORE_BUTTON_LOCATORS[0]);
                        wait.until(ExpectedConditions.elementToBeClickable(setMyStoreButton)).click();
                        return;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not set store as preferred: " + e.getMessage());
        }
    }
    
    public boolean isPreferredStoreConfirmationDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            By[] confirmationLocators = {
                By.xpath("//*[contains(text(),'Your Store') or contains(text(),'Preferred Store') or contains(text(),'Selected Store')]"),
                By.cssSelector("[data-qa='preferred-store']"),
                By.cssSelector(".preferred-store")
            };
            for (By locator : confirmationLocators) {
                try {
                    shortWait.until(ExpectedConditions.presenceOfElementLocated(locator));
                    return true;
                } catch (Exception e) {
                    continue;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isPreferredStoreShownInHeader() {
        try {
            By[] headerStoreLocators = {
                By.xpath("//header//*[contains(text(),'375 Washington Street') or contains(text(),'Boston')]"),
                By.cssSelector("header [data-qa='store-location']"),
                By.cssSelector("header .store-location")
            };
            for (By locator : headerStoreLocators) {
                try {
                    WebElement headerStore = driver.findElement(locator);
                    if (headerStore.isDisplayed()) {
                        return true;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}