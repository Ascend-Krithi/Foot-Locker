package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    private static final By[] FIND_STORE_LOCATORS = {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    private static final By[] SELECT_MY_STORE_LOCATORS = {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private static final By[] SEARCH_BUTTON_LOCATORS = {
        By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search Stores') or contains(.,'Find Stores')]"),
        By.cssSelector("button[type='submit']"),
        By.xpath("//button[@type='submit']")
    };

    private static final By COOKIE_ACCEPT_BUTTON = By.id("onetrust-accept-btn-handler");
    private static final By FLX_REWARDS_CLOSE = By.cssSelector("button[aria-label='Close'], .close, [class*='close']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        this.js = (JavascriptExecutor) driver;
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement cookieButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(COOKIE_ACCEPT_BUTTON));
            clickElementWithFallback(cookieButton);
            Thread.sleep(1000);
        } catch (Exception e) {
            // Cookie banner not present or already accepted
        }
    }

    public void closeFlxRewardsIfPresent() {
        try {
            WebElement closeButton = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(FLX_REWARDS_CLOSE));
            clickElementWithFallback(closeButton);
            Thread.sleep(1000);
        } catch (Exception e) {
            // Modal not present
        }
    }

    public void clickFindStore() {
        WebElement findStoreLink = findElementWithFallback(FIND_STORE_LOCATORS);
        clickElementWithFallback(findStoreLink);
    }

    public boolean isSelectMyStoreVisible() {
        try {
            WebElement selectMyStore = findElementWithFallback(SELECT_MY_STORE_LOCATORS);
            return selectMyStore.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        WebElement selectMyStore = findElementWithFallback(SELECT_MY_STORE_LOCATORS);
        clickElementWithFallback(selectMyStore);
    }

    public void clickSearchForStores() {
        WebElement searchButton = findElementWithFallback(SEARCH_BUTTON_LOCATORS);
        clickElementWithFallback(searchButton);
    }

    private WebElement findElementWithFallback(By[] locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (TimeoutException e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }

    private void clickElementWithFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }
    }
}