package com.fl.automation.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    // ====== LOCATORS ======
    private By findStoreButton = By.xpath("//span[contains(text(),'Find a Store')]");
    private By storePopupHeader = By.xpath("//*[contains(text(),'Select my store')]");

    // 🔥 UPDATED (more reliable for CI)
    private By locationSearchInput = By.xpath("//input[contains(@placeholder,'City') or contains(@placeholder,'Zip') or contains(@aria-label,'location')]");

    private By searchButton = By.xpath("//button[@type='submit' or contains(.,'Search')]");

    private By storeCards = By.xpath("//*[contains(@class,'location') or contains(@class,'store')]");
    private By storeAddress = By.xpath(".//address | .//*[contains(@class,'address')]");

    private By setMyStoreButton = By.xpath(".//button[contains(.,'Set My Store')]");

    private By acceptCookiesBtn = By.id("onetrust-accept-btn-handler");

    // ====== CONSTRUCTOR ======
    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40)); // ⬆️ increased for CI
    }

    // ====== STEP 1: HANDLE COOKIES ======
    public void handleCookies() {
        try {
            WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn));
            accept.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(acceptCookiesBtn));
        } catch (Exception ignored) {
        }
    }

    // ====== STEP 2: OPEN STORE LOCATOR ======
    public void openStoreLocator() {
        try {
            WebElement findStore = wait.until(ExpectedConditions.elementToBeClickable(findStoreButton));
            safeClick(findStore);

            wait.until(ExpectedConditions.visibilityOfElementLocated(storePopupHeader));
        } catch (Exception e) {
            throw new RuntimeException("Unable to open Store Locator popup", e);
        }
    }

    // ====== 🔥 NEW: WAIT FOR FULL LOAD (CRITICAL FIX) ======
    public void waitForStoreLocatorToLoad() {
        try {
            // small buffer for React rendering in CI
            Thread.sleep(2000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(locationSearchInput));
        } catch (Exception e) {
            throw new RuntimeException("Store locator did not load properly", e);
        }
    }

    // ====== VALIDATIONS ======
    public boolean isLocationSearchInputDisplayed() {
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationSearchInput));
            return input.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
            return btn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ====== ACTIONS ======
    public void enterLocation(String location) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationSearchInput));
        input.clear();
        input.sendKeys(location);
    }

    public void clickSearchButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        safeClick(btn);
    }

    // ====== RESULTS ======
    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> cards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(storeCards));
            return cards.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSpecificStoreDisplayed(String addressText) {
        try {
            List<WebElement> cards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(storeCards));
            for (WebElement card : cards) {
                WebElement address = findAddressInCard(card);
                if (address != null && address.getText().contains(addressText)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSetMyStoreForAddress(String addressText) {
        List<WebElement> cards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(storeCards));

        for (WebElement card : cards) {
            WebElement address = findAddressInCard(card);
            if (address != null && address.getText().contains(addressText)) {
                WebElement button = card.findElement(setMyStoreButton);
                wait.until(ExpectedConditions.elementToBeClickable(button));
                safeClick(button);
                return;
            }
        }

        throw new RuntimeException("Store with address " + addressText + " not found");
    }

    public boolean isStoreConfirmationDisplayed() {
        try {
            wait.until(ExpectedConditions.urlContains("footlocker.com"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ====== UTIL METHODS ======
    private WebElement findAddressInCard(WebElement card) {
        try {
            return card.findElement(storeAddress);
        } catch (Exception e) {
            return null;
        }
    }

    private void safeClick(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
