package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

/**
 * HomePage - Page Object for Footlocker homepage
 */
@Slf4j
public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    private static final String BASE_URL = "https://www.footlocker.com";

    // Locators with fallback strategy
    private static final By[] FIND_A_STORE_LOCATORS = {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]"),
        By.xpath("//*[normalize-space()='Find a Store' or normalize-space()='Find a store']")
    };

    private static final By[] COOKIE_ACCEPT_LOCATORS = {
        By.id("onetrust-accept-btn-handler"),
        By.cssSelector("button#onetrust-accept-btn-handler"),
        By.cssSelector("button[aria-label*='Accept' i]")
    };

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        this.js = (JavascriptExecutor) driver;
    }

    /**
     * Navigates to Footlocker homepage
     */
    public void navigateToHomePage() {
        log.info("Navigating to: {}", BASE_URL);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        
        // Wait for page to load
        wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
        log.info("Homepage loaded successfully");
        
        // Handle cookie consent if present
        handleCookieConsent();
    }

    /**
     * Handles cookie consent popup if present
     */
    private void handleCookieConsent() {
        try {
            for (By locator : COOKIE_ACCEPT_LOCATORS) {
                try {
                    WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(locator));
                    acceptButton.click();
                    log.info("Accepted cookie consent");
                    return;
                } catch (Exception ignored) {
                    // Try next locator
                }
            }
        } catch (Exception e) {
            log.info("No cookie consent popup found or already accepted");
        }
    }

    /**
     * Finds element using fallback locator strategy
     */
    private WebElement findElementWithFallback(By[] locators, String elementName) {
        log.info("Attempting to find element: {}", elementName);
        
        for (int i = 0; i < locators.length; i++) {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locators[i]));
                log.info("Found {} using locator strategy {}", elementName, i + 1);
                return element;
            } catch (Exception e) {
                log.warn("Locator strategy {} failed for {}: {}", i + 1, elementName, e.getMessage());
                if (i == locators.length - 1) {
                    throw new RuntimeException("Failed to find " + elementName + " with all fallback strategies");
                }
            }
        }
        throw new RuntimeException("Failed to find " + elementName);
    }

    /**
     * Clicks element with JavaScript fallback
     */
    private void clickWithFallback(WebElement element, String elementName) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            log.info("Clicked {} using standard click", elementName);
        } catch (Exception e) {
            log.warn("Standard click failed for {}, using JavaScript click", elementName);
            js.executeScript("arguments[0].click();", element);
            log.info("Clicked {} using JavaScript", elementName);
        }
    }

    /**
     * Clicks on 'Find a Store' link
     */
    public void clickFindAStore() {
        WebElement findAStoreElement = findElementWithFallback(FIND_A_STORE_LOCATORS, "Find a Store");
        clickWithFallback(findAStoreElement, "Find a Store");
        
        // Wait for popup/modal to appear
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Choose a preferred store')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Select My Store')]"))
            ));
            log.info("Store locator popup appeared");
        } catch (Exception e) {
            log.warn("Popup appearance verification timed out, continuing...");
        }
    }

    /**
     * Verifies if homepage is loaded
     */
    public boolean isHomePageLoaded() {
        try {
            String currentUrl = driver.getCurrentUrl();
            boolean isLoaded = currentUrl.contains("footlocker.com");
            log.info("Homepage loaded status: {}, Current URL: {}", isLoaded, currentUrl);
            return isLoaded;
        } catch (Exception e) {
            log.error("Failed to verify homepage load: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Verifies if popup message is displayed
     */
    public boolean isPopupMessageDisplayed(String expectedMessage) {
        try {
            By messageLocator = By.xpath("//*[contains(text(),'" + expectedMessage + "')]");
            WebElement messageElement = wait.until(ExpectedConditions.presenceOfElementLocated(messageLocator));
            boolean isDisplayed = messageElement.isDisplayed();
            log.info("Popup message '{}' displayed: {}", expectedMessage, isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            log.error("Popup message '{}' not found: {}", expectedMessage, e.getMessage());
            return false;
        }
    }
}