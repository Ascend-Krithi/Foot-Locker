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

    private By findStoreButton = By.xpath(
        "//a[contains(@href,'store-locator')] | " +
        "//span[contains(text(),'Find a Store')] | " +
        "//a[contains(normalize-space(),'Find a Store')] | " +
        "//button[contains(normalize-space(),'Find a Store')]"
    );

    private By selectMyStoreLink = By.xpath(
        "//a[contains(text(),'Select my store')] | //button[contains(text(),'Select my store')]"
    );

    private By storePopupHeader = By.xpath(
        "//*[contains(text(),'Find a Store') or contains(text(),'Store Locator')]"
    );

    private By searchButton = By.xpath(
        "//button[normalize-space()='Search for Stores']"
    );

    private By storeCards = By.xpath(
        "//*[contains(@class,'store-card') or contains(@class,'StoreCard') or " +
        "contains(@class,'store-result') or contains(@class,'location-card') or " +
        "@data-testid='store-card']"
    );

    private By storeAddress = By.xpath(
        ".//address | .//*[contains(@class,'address') or @data-testid='store-address']"
    );

    private By setMyStoreButton = By.xpath(
        ".//button[contains(normalize-space(),'Set My Store') or @data-testid='set-my-store']"
    );

    private By acceptCookiesBtn = By.id("onetrust-accept-btn-handler");

    private By storeConfirmationBanner = By.xpath(
        "//*[contains(text(),'My Store') or @data-testid='my-store-confirmation']"
    );

    // ====== CONSTRUCTOR ======
    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // ====== STEP 1: HANDLE COOKIES ======
    public void handleCookies() {
        try {
            WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn));
            accept.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(acceptCookiesBtn));
            System.out.println("[INFO] Cookie popup accepted.");
        } catch (Exception e) {
            System.out.println("[INFO] Cookie popup not present or already handled.");
        }
    }

    // ====== STEP 2: OPEN STORE LOCATOR ======
    public void openStoreLocator() {
        try {
            WebElement findStore = wait.until(ExpectedConditions.elementToBeClickable(findStoreButton));
            safeClick(findStore);
            System.out.println("[INFO] Clicked 'Find a Store'");

            // Click "Select my store" if present
            try {
                WebElement selectStore = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreLink));
                safeClick(selectStore);
                System.out.println("[INFO] Clicked 'Select my store'");
            } catch (Exception e) {
                System.out.println("[INFO] 'Select my store' not required");
            }

            wait.until(ExpectedConditions.visibilityOfElementLocated(storePopupHeader));
            System.out.println("[INFO] Store locator modal opened");

        } catch (Exception e) {
            throw new RuntimeException("❌ Unable to open Store Locator", e);
        }
    }

    // ====== 🔥 SHADOW DOM HANDLING ======
    private WebElement getShadowSearchInput() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Try multiple possible shadow hosts
            List<WebElement> hosts = driver.findElements(By.cssSelector("fl-store-locator, fl-store-selector"));

            for (WebElement host : hosts) {
                WebElement shadowRoot = (WebElement) js.executeScript(
                        "return arguments[0].shadowRoot", host);

                if (shadowRoot != null) {
                    List<WebElement> inputs = shadowRoot.findElements(By.cssSelector("input"));

                    if (!inputs.isEmpty()) {
                        System.out.println("[INFO] Found input inside Shadow DOM");
                        return inputs.get(0);
                    }
                }
            }

            throw new RuntimeException("Shadow input not found");

        } catch (Exception e) {
            throw new RuntimeException("❌ Unable to locate search input inside Shadow DOM", e);
        }
    }

    private WebElement getShadowSearchButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            List<WebElement> hosts = driver.findElements(By.cssSelector("fl-store-locator, fl-store-selector"));

            for (WebElement host : hosts) {
                WebElement shadowRoot = (WebElement) js.executeScript(
                        "return arguments[0].shadowRoot", host);

                if (shadowRoot != null) {
                    List<WebElement> buttons = shadowRoot.findElements(By.xpath(".//button"));

                    for (WebElement btn : buttons) {
                        if (btn.getText().contains("Search")) {
                            return btn;
                        }
                    }
                }
            }

            throw new RuntimeException("Search button not found");

        } catch (Exception e) {
            throw new RuntimeException("❌ Unable to locate search button inside Shadow DOM", e);
        }
    }

    // ====== ACTIONS ======
    public void enterLocation(String location) {
        WebElement input = getShadowSearchInput();
        input.clear();
        input.sendKeys(location);
        System.out.println("[INFO] Entered location: " + location);
    }

    public void clickSearchButton() {
        WebElement btn = getShadowSearchButton();
        safeClick(btn);
        System.out.println("[INFO] Clicked search button");
    }

    // ====== RESULTS ======
    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> cards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(storeCards)
            );
            System.out.println("[INFO] Store results: " + cards.size());
            return !cards.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSetMyStoreForAddress(String addressText) {
        List<WebElement> cards = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(storeCards)
        );

        for (WebElement card : cards) {
            WebElement address = findAddressInCard(card);

            if (address != null && address.getText().contains(addressText)) {
                WebElement button = card.findElement(setMyStoreButton);
                safeClick(button);
                System.out.println("[INFO] Set store for: " + addressText);
                return;
            }
        }

        throw new RuntimeException("❌ Store not found: " + addressText);
    }

    public boolean isStoreConfirmationDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeConfirmationBanner));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ====== UTIL ======
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
