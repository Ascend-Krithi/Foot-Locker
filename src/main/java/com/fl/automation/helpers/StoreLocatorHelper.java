package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    // ✅ Confirmed exact id from logs
    private By locationInput = By.id("StoreLocator_search_query");

    private By searchButton = By.xpath(
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

    // ✅ Wait for store locator input
    public void waitForStoreLocatorToLoad() {
        System.out.println("[INFO] Waiting for store locator input (id=StoreLocator_search_query)...");
        try {
            WebElement input = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(locationInput));
            System.out.println("[INFO] Store locator input found. placeholder='"
                + input.getAttribute("placeholder") + "'");
        } catch (Exception e) {
            throw new RuntimeException("Store locator input did not appear. " + e.getMessage());
        }
    }

    // ✅ Enter city — wait for autocomplete suggestion and click first result
    public void enterLocation(String city) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
        input.clear();
        input.sendKeys(city);
        System.out.println("[INFO] Typed city: " + city);

        // Wait for autocomplete suggestions to load
        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}

        // Try clicking first autocomplete suggestion
        List<WebElement> suggestions = driver.findElements(By.xpath(
            "//*[contains(@class,'pac-item')] | " +
            "//*[contains(@class,'suggestion')] | " +
            "//*[contains(@class,'Suggestion')] | " +
            "//*[contains(@class,'autocomplete')] | " +
            "//*[@role='option'] | " +
            "//*[@role='listbox']//*[@role='option'] | " +
            "//*[contains(@class,'StoreLocator')]//li[contains(@class,'item')]"
        ));

        System.out.println("[DEBUG] Suggestions found: " + suggestions.size());

        boolean clicked = false;
        for (WebElement s : suggestions) {
            try {
                if (s.isDisplayed() && !s.getText().trim().isEmpty()) {
                    System.out.println("[INFO] Selecting suggestion: '" + s.getText().trim() + "'");
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", s);
                    clicked = true;
                    try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                    break;
                }
            } catch (Exception ignored) {}
        }

        if (!clicked) {
            System.out.println("[WARN] No suggestion clicked, pressing ENTER.");
            input.sendKeys(Keys.ENTER);
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }

        // Wait for error to clear if any
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                    By.id("StoreLocatorErrors")));
            System.out.println("[INFO] No error alert present.");
        } catch (Exception ignored) {
            System.out.println("[WARN] Error alert may still be present.");
        }
    }

    // ✅ Enter default city "Boston"
    public void enterDefaultCity() {
        enterLocation(DEFAULT_CITY);
    }

    // ✅ Click Search — JS click to bypass any overlay
    public void clickSearchButton() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                    By.id("StoreLocatorErrors")));
        } catch (Exception ignored) {}

        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(searchButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        System.out.println("[INFO] Clicked Search for Stores button via JS");
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
