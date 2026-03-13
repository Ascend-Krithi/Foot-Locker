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

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    // FOOTLOCKER Header or Navigation Locators
    private static final By HEADER_FIND_A_STORE_LINK = By.xpath("//a[contains(@href,'store') and (contains(.,'Store') or contains(.,'store'))]");
    private static final By HEADER_FIND_A_STORE_NAV = By.cssSelector("nav a[href*='stores']");

    // Locator page elements
    private static final By SELECT_MY_STORE_LINK = By.xpath("//*[contains(text(),'Select My Store')]");
    private static final By SEARCH_INPUT = By.cssSelector("input[type='search'], input[name='q']");
    private static final By SEARCH_BTN = By.xpath("//button[contains(., 'Search') or contains(.,'Search for')]");
    private static final By STORE_RESULT_CARD = By.cssSelector("[data-qa='location'], .location-card, li.store");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        this.js = (JavascriptExecutor) driver;
    }

    // Makes the click with fallback so tests can call it
    public void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    public WebElement findStoreLink() {
        // Try multiple header nav options
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(HEADER_FIND_A_STORE_LINK));
        } catch (Exception ignored) {}
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(HEADER_FIND_A_STORE_NAV));
        } catch (Exception ignored) {}
        return null;
    }

    public WebElement findSelectMyStoreLink() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(SELECT_MY_STORE_LINK));
        } catch (Exception e) {
            return null;
        }
    }

    public WebElement findSearchInput() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        } catch (Exception e) {
            return null;
        }
    }

    public WebElement findSearchButton() {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BTN));
        } catch (Exception e) {
            return null;
        }
    }

    public List<WebElement> findStoreResultCards() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(STORE_RESULT_CARD));
            return driver.findElements(STORE_RESULT_CARD);
        } catch (Exception e) {
            return List.of();
        }
    }
}
