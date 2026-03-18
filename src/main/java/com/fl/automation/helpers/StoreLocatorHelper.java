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

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // ===================== LOCATORS =====================

    private By locationInput = By.id("StoreLocator_search_query");

    private By searchButton = By.xpath(
        "//button[contains(.,'Search for Stores')] | " +
        "//button[contains(.,'Search Stores')] | " +
        "//button[@type='submit' and contains(.,'Search')]"
    );

    private By storeResultsContainer = By.xpath(
        "//*[contains(@class,'store-card') or " +
        "contains(@class,'StoreCard') or " +
        "contains(@class,'store-details') or " +
        "contains(@class,'StoreDetails') or " +
        "contains(@class,'store-result') or " +
        "contains(@class,'StoreResult')]"
    );

    private By setMyStoreButton = By.xpath(
        "//button[contains(.,'Set my store')] | " +
        "//button[contains(.,'Set My Store')] | " +
        "//a[contains(.,'Update my store')] | " +
        "//button[contains(.,'Update my store')]"
    );

    private By storeConfirmation = By.xpath(
        "//*[contains(@class,'preferred-store') or " +
        "contains(@class,'PreferredStore') or " +
        "contains(@class,'store-confirmation') or " +
        "contains(text(),'preferred') or " +
        "contains(text(),'My Store')]"
    );

    private By storeNameHeader = By.xpath(
        "//*[contains(@class,'StoreLocator')]//h1 | " +
        "//*[contains(@class,'StoreLocator')]//h2 | " +
        "//*[contains(@class,'store-name')] | " +
        "//*[contains(@class,'StoreName')]"
    );

    // ===================== MODAL LOAD =====================

    public void waitForStoreLocatorToLoad() {
        System.out.println("[INFO] Waiting for store locator input...");
        try {
            WebElement input = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(locationInput));
            System.out.println("[INFO] Store locator input ready. placeholder='"
                + input.getAttribute("placeholder") + "'");
        } catch (Exception e) {
            throw new RuntimeException("Store locator input did not appear. " + e.getMessage());
        }
    }

    // ===================== INPUT & SEARCH =====================

    public void enterLocation(String location) {
        WebElement input = wait.until(
            ExpectedConditions.visibilityOfElementLocated(locationInput));
        input.clear();
        input.sendKeys(location);
        System.out.println("[INFO] Entered location: " + location);
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    public void clickSearchButton() {
        System.out.println("[INFO] Clicking 'Search for Stores'...");
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                    By.id("StoreLocatorErrors")));
        } catch (Exception ignored) {}
        WebElement btn = wait.until(
            ExpectedConditions.presenceOfElementLocated(searchButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        System.out.println("[INFO] Clicked 'Search for Stores'.");
    }

    public void searchForLocation(String location) {
        enterLocation(location);
        clickSearchButton();
    }

    // ===================== RESULTS =====================

    public void waitForStoreResults() {
        System.out.println("[INFO] Waiting for store results...");
        try {
            new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(storeResultsContainer));
            System.out.println("[INFO] Store results loaded.");
        } catch (Exception e) {
            System.out.println("[WARN] Store results container not found: " + e.getMessage());
        }
    }

    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> results = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(storeResultsContainer));
            System.out.println("[INFO] Store results count: " + results.size());
            return results.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStoreDisplayed(String storeText) {
        try {
            WebElement el = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'" + storeText + "')]")));
            System.out.println("[INFO] Store found: " + storeText);
            return el.isDisplayed();
        } catch (Exception e) {
            System.out.println("[WARN] Store not found: " + storeText);
            return false;
        }
    }

    public boolean isAddressDisplayed(String addressText) {
        try {
            WebElement el = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'" + addressText + "')]")));
            System.out.println("[INFO] Address found: " + addressText);
            return el.isDisplayed();
        } catch (Exception e) {
            System.out.println("[WARN] Address not found: " + addressText);
            return false;
        }
    }

    public int getStoreResultsCount() {
        try {
            List<WebElement> results = driver.findElements(storeResultsContainer);
            System.out.println("[INFO] Store results count: " + results.size());
            return results.size();
        } catch (Exception e) {
            return 0;
        }
    }

    // ===================== SET MY STORE =====================

    public void clickSetMyStoreForStore(String storeText) {
        try {
            WebElement storeCard = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[contains(text(),'" + storeText + "')]/ancestor::div" +
                        "[contains(@class,'store') or contains(@class,'Store')][1]")));
            WebElement btn = storeCard.findElement(By.xpath(
                ".//button[contains(.,'Set')] | " +
                ".//a[contains(.,'Update my store')] | " +
                ".//button[contains(.,'Update')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
            System.out.println("[INFO] Clicked Set/Update My Store for: " + storeText);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Set My Store for: "
                + storeText + " | " + e.getMessage());
        }
    }

    public void clickSetMyStoreForFirstResult() {
        try {
            WebElement btn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(setMyStoreButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
            System.out.println("[INFO] Clicked Set My Store for first result.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Set My Store: " + e.getMessage());
        }
    }

    // ===================== CONFIRMATION =====================

    public boolean isStoreConfirmationDisplayed() {
        try {
            WebElement el = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(storeConfirmation));
            System.out.println("[INFO] Store confirmation displayed: " + el.getText());
            return el.isDisplayed();
        } catch (Exception e) {
            System.out.println("[WARN] Store confirmation not found.");
            return false;
        }
    }

    public boolean isStoreNameInHeader(String storeName) {
        try {
            WebElement el = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(@class,'header') or contains(@class,'Header') or " +
                             "contains(@class,'nav') or contains(@class,'ribbon')]" +
                             "//*[contains(text(),'" + storeName + "')]")));
            System.out.println("[INFO] Store name found in header: " + storeName);
            return el.isDisplayed();
        } catch (Exception e) {
            System.out.println("[WARN] Store name not found in header: " + storeName);
            return false;
        }
    }

    // ===================== VALIDATION HELPERS =====================

    public boolean isLocationSearchInputDisplayed() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locationInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLocationInputValue() {
        try {
            WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locationInput));
            return input.getAttribute("value");
        } catch (Exception e) {
            return "";
        }
    }

    public void clearLocationInput() {
        try {
            WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locationInput));
            input.clear();
            System.out.println("[INFO] Location input cleared.");
        } catch (Exception e) {
            System.out.println("[WARN] Could not clear location input.");
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(By.id("StoreLocatorErrors")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessageText() {
        try {
            return driver.findElement(By.id("StoreLocatorErrors")).getText();
        } catch (Exception e) {
            return "";
        }
    }

    // ===================== BACKWARD COMPATIBILITY ALIASES =====================
    // These ensure AI-generated scripts using any method name variation compile successfully

    /** @deprecated Use isStoreDisplayed(String) */
    public boolean isSpecificStoreDisplayed(String storeText) {
        return isStoreDisplayed(storeText);
    }

    /** @deprecated Use clickSetMyStoreForStore(String) */
    public void clickSetMyStoreForAddress(String storeText) {
        clickSetMyStoreForStore(storeText);
    }

    /** @deprecated Use searchForLocation(String) */
    public void enterAndSearchLocation(String location) {
        searchForLocation(location);
    }

    /** @deprecated Use isStoreDisplayed(String) */
    public boolean isStoreResultDisplayed(String storeText) {
        return isStoreDisplayed(storeText);
    }

    /** @deprecated Use areStoreResultsDisplayed() */
    public boolean isStoreListDisplayed() {
        return areStoreResultsDisplayed();
    }

    /** @deprecated Use isStoreConfirmationDisplayed() */
    public boolean isStoreSetConfirmationDisplayed() {
        return isStoreConfirmationDisplayed();
    }

    /** @deprecated Use clickSetMyStoreForStore(String) */
    public void setStoreAsPreferred(String storeText) {
        clickSetMyStoreForStore(storeText);
    }

    /** @deprecated Use clickSetMyStoreForFirstResult() */
    public void setFirstStoreAsPreferred() {
        clickSetMyStoreForFirstResult();
    }

    /** @deprecated Use isStoreNameInHeader(String) */
    public boolean isPreferredStoreDisplayedInHeader(String storeName) {
        return isStoreNameInHeader(storeName);
    }

    /** @deprecated Use isAddressDisplayed(String) */
    public boolean isStoreAddressDisplayed(String addressText) {
        return isAddressDisplayed(addressText);
    }

    /** @deprecated Use waitForStoreResults() */
    public void waitForSearchResults() {
        waitForStoreResults();
    }

    /** @deprecated Use enterLocation(String) */
    public void typeLocation(String location) {
        enterLocation(location);
    }

    /** @deprecated Use clickSearchButton() */
    public void submitSearch() {
        clickSearchButton();
    }
}
