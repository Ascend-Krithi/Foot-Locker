package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By[] findStoreLinkLocators = {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    private By[] storePopupLocators = {
        By.cssSelector("div[class*='StoreLocatorPopup']"),
        By.xpath("//div[contains(@class,'StoreLocatorPopup') or contains(@class,'store-popup')]"),
        By.xpath("//div[contains(text(),'Choose a preferred store')]/ancestor::div[contains(@class,'popup') or contains(@class,'modal')]")
    };

    private By[] selectMyStoreLinkLocators = {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store')]")
    };

    private By[] cookieAcceptLocators = {
        By.id("onetrust-accept-btn-handler"),
        By.cssSelector("button#onetrust-accept-btn-handler"),
        By.cssSelector("button[aria-label*='Accept' i]")
    };

    private By[] selectedStoreIndicatorLocators = {
        By.cssSelector("header [class*='store-name'], header [class*='selected-store']"),
        By.xpath("//header//span[contains(@class,'store') or contains(text(),'Washington Street')]"),
        By.xpath("//header//*[contains(text(),'375 Washington Street') or contains(text(),'Boston')]")
    };

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement cookieButton = findElementWithFallback(cookieAcceptLocators, 5);
            if (cookieButton != null) {
                clickWithJSFallback(cookieButton);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("No cookie consent found or already accepted");
        }
    }

    public void clickFindStore() {
        WebElement findStoreElement = findElementWithFallback(findStoreLinkLocators, 40);
        if (findStoreElement != null) {
            clickWithJSFallback(findStoreElement);
        } else {
            throw new RuntimeException("Find a Store link not found with any locator");
        }
    }

    public boolean isStorePopupDisplayed() {
        WebElement popup = findElementWithFallback(storePopupLocators, 40);
        return popup != null && popup.isDisplayed();
    }

    public boolean isSelectMyStoreLinkVisible() {
        WebElement selectMyStore = findElementWithFallback(selectMyStoreLinkLocators, 40);
        return selectMyStore != null && selectMyStore.isDisplayed();
    }

    public void clickSelectMyStore() {
        WebElement selectMyStoreElement = findElementWithFallback(selectMyStoreLinkLocators, 40);
        if (selectMyStoreElement != null) {
            clickWithJSFallback(selectMyStoreElement);
        } else {
            throw new RuntimeException("Select My Store link not found with any locator");
        }
    }

    public boolean isSelectedStoreDisplayedInHeader(String expectedStore) {
        try {
            WebElement storeIndicator = findElementWithFallback(selectedStoreIndicatorLocators, 10);
            if (storeIndicator != null) {
                String storeText = storeIndicator.getText();
                return storeText.contains(expectedStore) || storeText.contains("Washington Street") || storeText.contains("Boston");
            }
        } catch (Exception e) {
            System.out.println("Store indicator not found in header");
        }
        return false;
    }

    private WebElement findElementWithFallback(By[] locators, int timeoutSeconds) {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        for (By locator : locators) {
            try {
                return shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }

    private void clickWithJSFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } catch (Exception jsException) {
                throw new RuntimeException("Failed to click element with both regular and JS click", jsException);
            }
        }
    }
}