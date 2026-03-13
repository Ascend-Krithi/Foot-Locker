package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final List<By> HEADER_FIND_A_STORE = Arrays.asList(
        By.linkText("Find a Store"),
        By.xpath("//*[normalize-space()='Find a Store' or normalize-space()='Find a store']"),
        By.cssSelector("a[href*='store-locator'], a[aria-label*='Find a Store' i], button[aria-label*='Find a Store' i]"),
        By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'find a store')]")
    );

    private static final List<By> SELECT_MY_STORE = Arrays.asList(
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    );

    private static final List<By> LOCATOR_INPUT = Arrays.asList(
        By.cssSelector("input[placeholder*='Enter address' i], input[placeholder*='city' i], input[placeholder*='ZIP' i]"),
        By.cssSelector("input[aria-label*='Location' i], input[type='search']"),
        By.cssSelector("input[name*='location' i], input[id*='location' i]")
    );

    private static final List<By> LOCATOR_SEARCH_BTN = Arrays.asList(
        By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]"),
        By.cssSelector("[aria-label*='Search for store' i], button[type='submit']"),
        By.cssSelector("button[aria-label*='Search' i]"),
        By.xpath("//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search')]")
    );

    private static final List<By> COOKIE_ACCEPT = Arrays.asList(
        By.id("onetrust-accept-btn-handler"),
        By.cssSelector("button#onetrust-accept-btn-handler"),
        By.cssSelector("button[aria-label*='Accept' i]"),
        By.xpath("//button[contains(normalize-space(.),'Accept All Cookies') or contains(normalize-space(.),'Accept Cookies') or contains(normalize-space(.),'I Accept') or contains(normalize-space(.),'Accept All')]")
    );

    private static final List<By> MODAL_CLOSE = Arrays.asList(
        By.cssSelector("button[aria-label='Close'], button[aria-label*='close' i]"),
        By.xpath("//button[@aria-label='Close' or contains(@class,'close') or contains(@data-action,'close')]")
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void navigateToHomePage() {
        driver.get("https://www.footlocker.com");
        handleCookieConsent();
        handleModalPopups();
    }

    private void handleCookieConsent() {
        try {
            WebElement cookieButton = findElementWithFallback(COOKIE_ACCEPT, 5);
            if (cookieButton != null) {
                clickElement(cookieButton);
                wait.until(ExpectedConditions.invisibilityOf(cookieButton));
            }
        } catch (Exception e) {
        }
    }

    private void handleModalPopups() {
        try {
            WebElement closeButton = findElementWithFallback(MODAL_CLOSE, 3);
            if (closeButton != null) {
                clickElement(closeButton);
                wait.until(ExpectedConditions.invisibilityOf(closeButton));
            }
        } catch (Exception e) {
        }
    }

    public void clickFindAStore() {
        WebElement findStoreLink = findElementWithFallback(HEADER_FIND_A_STORE, 40);
        if (findStoreLink == null) {
            throw new RuntimeException("Find a Store link not found with any locator strategy");
        }
        clickElement(findStoreLink);
    }

    public boolean isSelectMyStoreVisible() {
        WebElement selectMyStore = findElementWithFallback(SELECT_MY_STORE, 40);
        return selectMyStore != null && selectMyStore.isDisplayed();
    }

    public void clickSelectMyStore() {
        WebElement selectMyStore = findElementWithFallback(SELECT_MY_STORE, 40);
        if (selectMyStore == null) {
            throw new RuntimeException("Select My Store link not found");
        }
        clickElement(selectMyStore);
    }

    public boolean isLocationInputVisible() {
        WebElement locationInput = findElementWithFallback(LOCATOR_INPUT, 40);
        return locationInput != null && locationInput.isDisplayed();
    }

    public boolean isSearchButtonVisible() {
        WebElement searchButton = findElementWithFallback(LOCATOR_SEARCH_BTN, 40);
        return searchButton != null && searchButton.isDisplayed();
    }

    public void enterLocation(String location) {
        WebElement locationInput = findElementWithFallback(LOCATOR_INPUT, 40);
        if (locationInput == null) {
            throw new RuntimeException("Location input not found");
        }
        locationInput.clear();
        locationInput.sendKeys(location);
    }

    public void clickSearchForStores() {
        WebElement searchButton = findElementWithFallback(LOCATOR_SEARCH_BTN, 40);
        if (searchButton == null) {
            throw new RuntimeException("Search for Stores button not found");
        }
        clickElement(searchButton);
    }

    private WebElement findElementWithFallback(List<By> locators, int timeoutSeconds) {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        for (By locator : locators) {
            try {
                return shortWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }

    private void clickElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);
            } catch (Exception jsException) {
                throw new RuntimeException("Failed to click element with both regular and JS click", jsException);
            }
        }
    }
}