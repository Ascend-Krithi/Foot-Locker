package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String DEFAULT_CITY = "Boston";

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // ✅ Broad locator — will be pinned after next run reveals exact attributes
    private By locationInput = By.xpath(
        "//*[contains(@class,'StoreLocatorDropdown')]//input | " +
        "//*[contains(@class,'store-locator')]//input | " +
        "//*[contains(@class,'StoreLocator')]//input | " +
        "//*[contains(@class,'modal')]//input | " +
        "//*[contains(@class,'panel')]//input | " +
        "//*[contains(@class,'drawer')]//input | " +
        "//*[contains(@class,'sidebar')]//input | " +
        "//input[contains(@placeholder,'address') or " +
                "contains(@placeholder,'city') or " +
                "contains(@placeholder,'post code') or " +
                "contains(@placeholder,'zip') or " +
                "contains(@placeholder,'store') or " +
                "contains(@placeholder,'location') or " +
                "contains(@placeholder,'search')]"
    );

    private By searchButton = By.xpath(
        "//*[contains(@class,'StoreLocatorDropdown')]//button[contains(.,'Search')] | " +
        "//*[contains(@class,'StoreLocator')]//button[contains(.,'Search')] | " +
        "//button[contains(.,'Search for Stores')] | " +
        "//button[contains(.,'Search Stores')] | " +
        "//button[@type='submit' and contains(.,'Search')]"
    );

    private By storeResults = By.xpath(
        "//div[contains(@class,'store') or contains(@class,'result')]"
    );

    private By confirmationMessage = By.xpath(
        "//*[contains(text(),'store') and contains(text(),'set')]"
    );

    // ✅ Wait for store locator input — logs everything for diagnosis
    public void waitForStoreLocatorToLoad() {
        System.out.println("[INFO] Checking page state for store locator input...");
        System.out.println("[DEBUG] Current URL: " + driver.getCurrentUrl());

        // Log ALL inputs on page
        List<WebElement> allInputs = driver.findElements(By.tagName("input"));
        System.out.println("[DEBUG] Total inputs on page: " + allInputs.size());
        allInputs.forEach(el -> {
            try {
                System.out.println("[DEBUG] Input: placeholder='" + el.getAttribute("placeholder")
                    + "' id='" + el.getAttribute("id")
                    + "' class='" + el.getAttribute("class")
                    + "' type='" + el.getAttribute("type")
                    + "' visible=" + el.isDisplayed());
            } catch (Exception ignored) {}
        });

        // Try to find and wait for the location input
        try {
            WebElement input = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(locationInput));
            System.out.println("[INFO] Store locator input found: placeholder='"
                + input.getAttribute("placeholder")
                + "' id='" + input.getAttribute("id") + "'");
        } catch (Exception e) {
            throw new RuntimeException("Store locator input did not appear. " + e.getMessage());
        }
    }

    // ✅ Enter city only — post code ignored
    public void enterLocation(String city) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
        input.clear();
        input.sendKeys(city);
        System.out.println("[INFO] Entered city: " + city);
        input.sendKeys(Keys.ENTER);
    }

    // ✅ Enter default city "Boston"
    public void enterDefaultCity() {
        enterLocation(DEFAULT_CITY);
    }

    // ✅ Click Search for Stores
    public void clickSearchButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
        System.out.println("[INFO] Clicked Search for Stores button");
    }

    // ✅ Validate results
    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> results = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(storeResults));
            return results.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Validate specific store
    public boolean isSpecificStoreDisplayed(String storeText) {
        try {
            return driver.findElement(
                By.xpath("//*[contains(text(),'" + storeText + "')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Click Set My Store
    public void clickSetMyStoreForAddress(String storeText) {
        try {
            WebElement store = driver.findElement(
                By.xpath("//*[contains(text(),'" + storeText + "')]/ancestor::div"));
            WebElement btn = store.findElement(By.xpath(".//button[contains(.,'Set')]"));
            btn.click();
            System.out.println("[INFO] Clicked Set My Store for: " + storeText);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Set My Store for: " + storeText);
        }
    }

    // ✅ Confirmation
    public boolean isStoreConfirmationDisplayed() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(confirmationMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ TC002 — checks location input is visible
    public boolean isLocationSearchInputDisplayed() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locationInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ TC002 — checks Search for Stores button is visible
    public boolean isSearchButtonDisplayed() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
