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

    // Locator fallback arrays
    private static final List<By> COOKIE_ACCEPT_LOCATORS = Arrays.asList(
        By.id("onetrust-accept-btn-handler"),
        By.cssSelector("button#onetrust-accept-btn-handler"),
        By.cssSelector("button[aria-label*='Accept' i]"),
        By.xpath("//button[contains(normalize-space(.),'Accept All Cookies')]")
    );

    private static final List<By> HEADER_FIND_STORE_LOCATORS = Arrays.asList(
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store')]"),
        By.xpath("//*[normalize-space()='Find a Store']")
    );

    private static final List<By> SELECT_MY_STORE_LOCATORS = Arrays.asList(
        By.xpath("//a[contains(.,'Select My Store')]"),
        By.xpath("//button[contains(.,'Select My Store')]"),
        By.xpath("//*[self::a or self::button][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'select my store')]")
    );

    private static final List<By> LOCATOR_INPUT_LOCATORS = Arrays.asList(
        By.cssSelector("input[placeholder*='Enter address' i]"),
        By.cssSelector("input[aria-label*='Location' i]"),
        By.cssSelector("input[name*='location' i]"),
        By.xpath("//input[@placeholder='Enter address, city or post code']")
    );

    private static final List<By> LOCATOR_SEARCH_BTN_LOCATORS = Arrays.asList(
        By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]"),
        By.cssSelector("[aria-label*='Search for store' i]"),
        By.cssSelector("button[aria-label*='Search' i]"),
        By.xpath("//button[contains(text(),'Search for Stores')]")
    );

    private static final List<By> STORE_RESULT_CARDS_LOCATORS = Arrays.asList(
        By.cssSelector("[data-qa='location']"),
        By.cssSelector(".c-location-card")
    );

    private static final List<By> STORE_ADDRESS_INSIDE_CARD_LOCATORS = Arrays.asList(
        By.cssSelector("[data-qa='address']"),
        By.cssSelector(".c-address")
    );

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public void acceptCookies() {
        WebElement cookieButton = findElementWithFallback(COOKIE_ACCEPT_LOCATORS);
        if (cookieButton != null) {
            clickWithJsFallback(cookieButton);
        }
    }

    public void clickFindStore() {
        WebElement findStoreLink = findElementWithFallback(HEADER_FIND_STORE_LOCATORS);
        clickWithJsFallback(findStoreLink);
    }

    public void clickSelectMyStore() {
        WebElement selectMyStoreBtn = findElementWithFallback(SELECT_MY_STORE_LOCATORS);
        clickWithJsFallback(selectMyStoreBtn);
    }

    public void enterLocation(String location) {
        WebElement locationInput = findElementWithFallback(LOCATOR_INPUT_LOCATORS);
        locationInput.clear();
        locationInput.sendKeys(location);
    }

    public void clickSearchButton() {
        WebElement searchButton = findElementWithFallback(LOCATOR_SEARCH_BTN_LOCATORS);
        clickWithJsFallback(searchButton);
    }

    public List<WebElement> getStoreResultCards() {
        for (By locator : STORE_RESULT_CARDS_LOCATORS) {
            try {
                List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
                if (!elements.isEmpty()) {
                    return elements;
                }
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Store result cards not found with any fallback locator");
    }

    public String getStoreAddress(WebElement storeCard) {
        for (By locator : STORE_ADDRESS_INSIDE_CARD_LOCATORS) {
            try {
                WebElement addressElement = storeCard.findElement(locator);
                return addressElement.getText();
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Store address not found within card");
    }

    public boolean isStoreLocatorInputDisplayed() {
        try {
            WebElement input = findElementWithFallback(LOCATOR_INPUT_LOCATORS);
            return input.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement findElementWithFallback(List<By> locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any fallback locator");
    }

    private void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}