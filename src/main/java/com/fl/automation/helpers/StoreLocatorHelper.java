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
    
    private static final By FIND_A_STORE_LINK = By.linkText("Find a Store");
    private static final By FIND_A_STORE_LINK_ALT1 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private static final By FIND_A_STORE_LINK_ALT2 = By.xpath("//header//a[contains(.,'Find a Store')]");
    
    private static final By SELECT_MY_STORE_LINK = By.xpath("//a[contains(.,'Select My Store')]");
    private static final By SELECT_MY_STORE_BUTTON = By.xpath("//button[contains(.,'Select My Store')]");
    
    private static final By LOCATION_INPUT = By.cssSelector("input[placeholder*='Enter address']");
    private static final By LOCATION_INPUT_ALT1 = By.cssSelector("input[aria-label*='Location']");
    private static final By LOCATION_INPUT_ALT2 = By.cssSelector("input[type='search']");
    
    private static final By SEARCH_BUTTON = By.xpath("//*[self::button or self::a][contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]");
    private static final By SEARCH_BUTTON_ALT = By.cssSelector("button[type='submit']");
    
    private static final By STORE_RESULTS = By.cssSelector("[data-qa='location']");
    private static final By STORE_RESULTS_ALT = By.cssSelector(".c-location-card");
    
    private static final By SET_MY_STORE_BUTTON = By.xpath(".//button[contains(.,'Set My Store')]");
    
    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }
    
    public void clickFindAStore() {
        WebElement findAStoreLink = findElementWithFallback(
            FIND_A_STORE_LINK, 
            FIND_A_STORE_LINK_ALT1, 
            FIND_A_STORE_LINK_ALT2
        );
        clickWithJSFallback(findAStoreLink);
    }
    
    public void clickSelectMyStore() {
        WebElement selectMyStoreElement = findElementWithFallback(
            SELECT_MY_STORE_LINK, 
            SELECT_MY_STORE_BUTTON
        );
        clickWithJSFallback(selectMyStoreElement);
    }
    
    public void enterLocation(String location) {
        WebElement locationInput = findElementWithFallback(
            LOCATION_INPUT, 
            LOCATION_INPUT_ALT1, 
            LOCATION_INPUT_ALT2
        );
        wait.until(ExpectedConditions.elementToBeClickable(locationInput));
        locationInput.clear();
        locationInput.sendKeys(location);
    }
    
    public void clickSearchButton() {
        WebElement searchButton = findElementWithFallback(
            SEARCH_BUTTON, 
            SEARCH_BUTTON_ALT
        );
        clickWithJSFallback(searchButton);
    }
    
    public List<WebElement> getStoreResults() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(STORE_RESULTS));
            return driver.findElements(STORE_RESULTS);
        } catch (Exception e) {
            wait.until(ExpectedConditions.presenceOfElementLocated(STORE_RESULTS_ALT));
            return driver.findElements(STORE_RESULTS_ALT);
        }
    }
    
    public void setPreferredStore(int storeIndex) {
        List<WebElement> stores = getStoreResults();
        if (storeIndex < stores.size()) {
            WebElement store = stores.get(storeIndex);
            WebElement setMyStoreButton = store.findElement(SET_MY_STORE_BUTTON);
            clickWithJSFallback(setMyStoreButton);
        }
    }
    
    public boolean isStoreLocatorPopupDisplayed() {
        try {
            WebElement locationInput = findElementWithFallback(
                LOCATION_INPUT, 
                LOCATION_INPUT_ALT1, 
                LOCATION_INPUT_ALT2
            );
            return locationInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSearchButtonDisplayed() {
        try {
            WebElement searchButton = findElementWithFallback(
                SEARCH_BUTTON, 
                SEARCH_BUTTON_ALT
            );
            return searchButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getStoreAddress(int storeIndex) {
        List<WebElement> stores = getStoreResults();
        if (storeIndex < stores.size()) {
            return stores.get(storeIndex).getText();
        }
        return "";
    }
    
    public boolean verifyStoreInResults(String expectedAddress) {
        List<WebElement> stores = getStoreResults();
        for (WebElement store : stores) {
            if (store.getText().contains(expectedAddress)) {
                return true;
            }
        }
        return false;
    }
    
    private WebElement findElementWithFallback(By... locators) {
        for (By locator : locators) {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element != null) {
                    return element;
                }
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }
    
    private void clickWithJSFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}