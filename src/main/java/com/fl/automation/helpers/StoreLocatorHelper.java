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
    // FIX #1: Use more robust locators that target specific, unique attributes.
    // If these still fail, right-click the element on footlocker.com → Inspect
    // and update the XPath/CSS to match the real DOM attribute you see.
 
    private By findStoreButton = By.xpath(
        "//a[contains(@href,'store-locator')] | //span[contains(text(),'Find a Store')] | //a[contains(text(),'Find a Store')]"
    );
 
    private By storePopupHeader = By.xpath(
        "//*[contains(text(),'Select my store') or contains(text(),'Find a Store') or contains(text(),'Store Locator')]"
    );
 
    // FIX #1 (continued): Broadened to cover more real-world Foot Locker DOM patterns.
    // Inspect the actual input on the live page and swap in the real selector if needed.
    private By locationSearchInput = By.xpath(
        "//input[@type='search'] | //input[contains(@placeholder,'City') or contains(@placeholder,'Zip') or contains(@placeholder,'city') or contains(@placeholder,'zip') or contains(@placeholder,'location')] | //input[contains(@aria-label,'location') or contains(@aria-label,'search') or contains(@aria-label,'Search')] | //input[@id='store-search'] | //input[@name='q']"
    );
 
    private By searchButton = By.xpath(
        "//button[@type='submit'] | //button[contains(@aria-label,'search') or contains(@aria-label,'Search')] | //button[normalize-space()='Search'] | //button[normalize-space()='Find Stores']"
    );
 
    // FIX #4: Scoped storeCards to result-specific containers only.
    // Avoids grabbing nav/footer elements that also contain "store" in class names.
    private By storeCards = By.xpath(
        "//*[contains(@class,'store-card') or contains(@class,'StoreCard') or contains(@class,'store-result') or contains(@class,'location-card') or @data-testid='store-card']"
    );
 
    private By storeAddress = By.xpath(
        ".//address | .//*[contains(@class,'address') or contains(@class,'Address') or @data-testid='store-address']"
    );
 
    private By setMyStoreButton = By.xpath(
        ".//button[contains(normalize-space(),'Set My Store') or contains(normalize-space(),'Set as My Store') or @data-testid='set-my-store']"
    );
 
    private By acceptCookiesBtn = By.id("onetrust-accept-btn-handler");
 
    // FIX #5: Proper confirmation locator — checks for a real UI element, not just the URL.
    private By storeConfirmationBanner = By.xpath(
        "//*[contains(@class,'my-store') or contains(@class,'MyStore') or contains(text(),'My Store') or @data-testid='my-store-confirmation']"
    );
 
    // ====== CONSTRUCTOR ======
    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
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
            System.out.println("[INFO] Clicked 'Find a Store' button.");
 
            wait.until(ExpectedConditions.visibilityOfElementLocated(storePopupHeader));
            System.out.println("[INFO] Store locator popup header is visible.");
        } catch (TimeoutException e) {
            // FIX #3: Preserve original exception + include URL for easier debugging in CI logs.
            throw new RuntimeException(
                "Unable to open Store Locator popup. Current URL: " + driver.getCurrentUrl() +
                " | Page title: " + driver.getTitle(), e
            );
        }
    }
 
    // ====== STEP 3: WAIT FOR STORE LOCATOR INPUT TO LOAD ======
    public void waitForStoreLocatorToLoad() {
        try {
            // FIX #2: Removed Thread.sleep(2000) — WebDriverWait polls every 500ms already.
            // Hard sleeps are unreliable in CI and add unnecessary delay.
            wait.until(ExpectedConditions.visibilityOfElementLocated(locationSearchInput));
            System.out.println("[INFO] Store locator search input is visible and ready.");
        } catch (TimeoutException e) {
            // FIX #3: Include page source snippet and URL to help diagnose XPath mismatches in CI.
            String pageSnippet = driver.getPageSource()
                .replaceAll("<[^>]+>", " ")   // strip tags
                .replaceAll("\\s+", " ")       // collapse whitespace
                .trim();
            int snippetEnd = Math.min(pageSnippet.length(), 500);
            throw new RuntimeException(
                "Store locator search input did not appear within 40s. " +
                "Current URL: " + driver.getCurrentUrl() + " | " +
                "Page text (first 500 chars): " + pageSnippet.substring(0, snippetEnd), e
            );
        }
    }
 
    // ====== VALIDATIONS ======
    public boolean isLocationSearchInputDisplayed() {
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationSearchInput));
            return input.isDisplayed();
        } catch (Exception e) {
            System.out.println("[WARN] Location search input not displayed: " + e.getMessage());
            return false;
        }
    }
 
    public boolean isSearchButtonDisplayed() {
        try {
            WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
            return btn.isDisplayed();
        } catch (Exception e) {
            System.out.println("[WARN] Search button not displayed: " + e.getMessage());
            return false;
        }
    }
 
    // ====== ACTIONS ======
    public void enterLocation(String location) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationSearchInput));
        input.clear();
        input.sendKeys(location);
        System.out.println("[INFO] Entered location: " + location);
    }
 
    public void clickSearchButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        safeClick(btn);
        System.out.println("[INFO] Clicked search button.");
    }
 
    // ====== RESULTS ======
    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> cards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(storeCards)
            );
            System.out.println("[INFO] Store results count: " + cards.size());
            return !cards.isEmpty();
        } catch (Exception e) {
            System.out.println("[WARN] No store result cards found: " + e.getMessage());
            return false;
        }
    }
 
    public boolean isSpecificStoreDisplayed(String addressText) {
        try {
            List<WebElement> cards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(storeCards)
            );
            for (WebElement card : cards) {
                WebElement address = findAddressInCard(card);
                if (address != null && address.getText().contains(addressText)) {
                    System.out.println("[INFO] Found store with address: " + addressText);
                    return true;
                }
            }
            System.out.println("[WARN] Store with address '" + addressText + "' not found in results.");
            return false;
        } catch (Exception e) {
            System.out.println("[WARN] Error checking for specific store: " + e.getMessage());
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
                wait.until(ExpectedConditions.elementToBeClickable(button));
                safeClick(button);
                System.out.println("[INFO] Clicked 'Set My Store' for address: " + addressText);
                return;
            }
        }
        throw new RuntimeException(
            "Store with address '" + addressText + "' not found. " +
            "Total cards found: " + cards.size() + ". " +
            "Current URL: " + driver.getCurrentUrl()
        );
    }
 
    // FIX #5: Check for a real confirmation UI element, not just the URL.
    public boolean isStoreConfirmationDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeConfirmationBanner));
            System.out.println("[INFO] Store confirmation banner is visible.");
            return true;
        } catch (Exception e) {
            System.out.println("[WARN] Store confirmation banner not found: " + e.getMessage());
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
        } catch (ElementClickInterceptedException e) {
            System.out.println("[INFO] Direct click intercepted, falling back to JS click.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
