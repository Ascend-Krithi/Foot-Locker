package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class StoreLocatorHelper {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final List<By> LOCATOR_INPUT = Arrays.asList(
        By.cssSelector("input[placeholder*='Enter address' i]"),
        By.cssSelector("input[aria-label*='Location' i]"),
        By.cssSelector("input[name*='location' i]")
    );

    private static final List<By> LOCATOR_SEARCH_BTN = Arrays.asList(
        By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]"),
        By.cssSelector("[aria-label*='Search for store' i]"),
        By.cssSelector("button[type='submit']")
    );

    private static final List<By> SEARCH_RESULTS = Arrays.asList(
        By.cssSelector("[class*='store-result']"),
        By.cssSelector("[class*='store-list'] [class*='store']"),
        By.xpath("//div[contains(@class,'store') or contains(@class,'result')]"),
        By.cssSelector("ul[class*='store'] li")
    );

    private static final List<By> SET_MY_STORE_BUTTON = Arrays.asList(
        By.xpath("//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'set my store')]"),
        By.cssSelector("button[aria-label*='Set My Store' i]"),
        By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'set my store')]")
    );

    private static final List<By> PREFERRED_STORE_INDICATOR = Arrays.asList(
        By.cssSelector("[class*='preferred-store']"),
        By.cssSelector("[class*='selected-store']"),
        By.xpath("//*[contains(@class,'store') and contains(@class,'selected')]"),
        By.xpath("//*[contains(text(),'Preferred Store') or contains(text(),'Selected Store')]")
    );

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public boolean isLocationInputDisplayed() {
        try {
            WebElement input = findElementWithFallback(LOCATOR_INPUT, 20);
            return input != null && input.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            WebElement button = findElementWithFallback(LOCATOR_SEARCH_BTN, 20);
            return button != null && button.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        WebElement input = findElementWithFallback(LOCATOR_INPUT, 40);
        wait.until(ExpectedConditions.visibilityOf(input));
        input.clear();
        input.sendKeys(location);
    }

    public void clickSearchButton() {
        WebElement button = findElementWithFallback(LOCATOR_SEARCH_BTN, 40);
        clickElement(button);
    }

    public boolean areSearchResultsDisplayed() {
        try {
            WebElement results = findElementWithFallback(SEARCH_RESULTS, 30);
            return results != null && results.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStoreAddressVisible(String address) {
        try {
            By addressLocator = By.xpath("//*[contains(text(),'" + address + "')]");
            WebElement addressElement = wait.until(ExpectedConditions.presenceOfElementLocated(addressLocator));
            return addressElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSetMyStoreForAddress(String address) {
        try {
            By storeContainer = By.xpath("//*[contains(text(),'" + address + "')]/ancestor::*[contains(@class,'store') or contains(@class,'result')][1]");
            WebElement container = wait.until(ExpectedConditions.presenceOfElementLocated(storeContainer));
            
            for (By locator : SET_MY_STORE_BUTTON) {
                try {
                    WebElement button = container.findElement(locator);
                    clickElement(button);
                    return;
                } catch (Exception e) {
                    // Try next locator
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not find Set My Store button for address: " + address);
        }
    }

    public boolean isPreferredStoreSet() {
        try {
            WebElement indicator = findElementWithFallback(PREFERRED_STORE_INDICATOR, 20);
            return indicator != null && indicator.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPreferredStoreDisplayedInHeader() {
        try {
            WebElement indicator = findElementWithFallback(PREFERRED_STORE_INDICATOR, 10);
            return indicator != null && indicator.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement findElementWithFallback(List<By> locators, int timeoutSeconds) {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        
        for (By locator : locators) {
            try {
                return shortWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception e) {
                // Try next locator
            }
        }
        
        throw new RuntimeException("Element not found with any of the fallback locators");
    }

    private void clickElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}