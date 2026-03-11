package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;

/**
 * StoreLocatorHelper - Contains all store locator related actions and locators
 */
@Slf4j
public class StoreLocatorHelper {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    // Locators with fallback strategy
    private static final By[] SELECT_MY_STORE_LOCATORS = {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private static final By[] LOCATION_INPUT_LOCATORS = {
        By.cssSelector("input[placeholder*='Enter address' i], input[placeholder*='city' i], input[placeholder*='ZIP' i]"),
        By.cssSelector("input[aria-label*='Location' i], input[type='search']"),
        By.cssSelector("input[name*='location' i], input[id*='location' i]")
    };

    private static final By[] SEARCH_BUTTON_LOCATORS = {
        By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]"),
        By.cssSelector("[aria-label*='Search for store' i], button[type='submit']"),
        By.cssSelector("button[aria-label*='Search' i]")
    };

    private static final By[] STORE_RESULT_LOCATORS = {
        By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']")
    };

    private static final By[] MODAL_CLOSE_LOCATORS = {
        By.cssSelector("button[aria-label='Close'], button[aria-label*='close' i]"),
        By.xpath("//button[@aria-label='Close' or contains(@class,'close') or contains(@data-action,'close')]")
    };

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        this.js = (JavascriptExecutor) driver;
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
     * Clicks on 'Select My Store' link/button
     */
    public void clickSelectMyStore() {
        WebElement selectMyStoreElement = findElementWithFallback(SELECT_MY_STORE_LOCATORS, "Select My Store");
        clickWithFallback(selectMyStoreElement, "Select My Store");
        
        // Wait for store locator popup to be visible
        wait.until(ExpectedConditions.or(
            ExpectedConditions.presenceOfElementLocated(LOCATION_INPUT_LOCATORS[0]),
            ExpectedConditions.presenceOfElementLocated(LOCATION_INPUT_LOCATORS[1]),
            ExpectedConditions.presenceOfElementLocated(LOCATION_INPUT_LOCATORS[2])
        ));
        log.info("Store locator popup opened successfully");
    }

    /**
     * Checks if 'Select My Store' link is visible
     */
    public boolean isSelectMyStoreLinkVisible() {
        try {
            WebElement element = findElementWithFallback(SELECT_MY_STORE_LOCATORS, "Select My Store");
            boolean isDisplayed = element.isDisplayed();
            log.info("Select My Store link visibility: {}", isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            log.error("Select My Store link not visible: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Checks if Location textbox is present
     */
    public boolean isLocationTextboxPresent() {
        try {
            WebElement element = findElementWithFallback(LOCATION_INPUT_LOCATORS, "Location Textbox");
            boolean isDisplayed = element.isDisplayed();
            log.info("Location textbox presence: {}", isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            log.error("Location textbox not present: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Checks if Search for Stores button is present
     */
    public boolean isSearchButtonPresent() {
        try {
            WebElement element = findElementWithFallback(SEARCH_BUTTON_LOCATORS, "Search Button");
            boolean isDisplayed = element.isDisplayed();
            log.info("Search button presence: {}", isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            log.error("Search button not present: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Enters location in the textbox
     */
    public void enterLocation(String location) {
        WebElement locationInput = findElementWithFallback(LOCATION_INPUT_LOCATORS, "Location Textbox");
        wait.until(ExpectedConditions.elementToBeClickable(locationInput));
        locationInput.clear();
        locationInput.sendKeys(location);
        log.info("Entered location: {}", location);
    }

    /**
     * Clicks Search for Stores button
     */
    public void clickSearchButton() {
        WebElement searchButton = findElementWithFallback(SEARCH_BUTTON_LOCATORS, "Search Button");
        clickWithFallback(searchButton, "Search Button");
        log.info("Clicked Search for Stores button");
    }

    /**
     * Waits for and verifies store results are displayed
     */
    public boolean areStoreResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(STORE_RESULT_LOCATORS[0])
            ));
            
            List<WebElement> results = driver.findElements(STORE_RESULT_LOCATORS[0]);
            boolean hasResults = results.size() > 0;
            log.info("Store results displayed: {}, Count: {}", hasResults, results.size());
            return hasResults;
        } catch (Exception e) {
            log.error("Store results not displayed: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Closes modal if present
     */
    public void closeModalIfPresent() {
        try {
            for (By locator : MODAL_CLOSE_LOCATORS) {
                try {
                    WebElement closeButton = driver.findElement(locator);
                    if (closeButton.isDisplayed()) {
                        clickWithFallback(closeButton, "Modal Close Button");
                        log.info("Closed modal");
                        return;
                    }
                } catch (Exception ignored) {
                    // Continue to next locator
                }
            }
        } catch (Exception e) {
            log.info("No modal to close or already closed");
        }
    }
}