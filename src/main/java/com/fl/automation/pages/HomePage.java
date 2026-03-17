package com.fl.automation.pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import java.time.Duration;
 
public class HomePage {
 
    private WebDriver driver;
    private WebDriverWait wait;
 
    // ===== LOCATORS =====
 
    // FIX #1: Broadened to match <a>, <span>, or <button> wrappers — consistent
    // with StoreLocatorHelper. If still failing, inspect footlocker.com and replace
    // with the real attribute (e.g. data-testid, href, aria-label).
    private By findStoreButton = By.xpath(
        "//a[contains(@href,'store-locator')] | " +
        "//a[contains(normalize-space(),'Find a Store')] | " +
        "//span[contains(normalize-space(),'Find a Store')] | " +
        "//button[contains(normalize-space(),'Find a Store')]"
    );
 
    // FIX #4: selectMyStoreText is kept here only to verify the popup opened.
    // All interactions inside the popup belong in StoreLocatorHelper — not here.
    private By selectMyStoreText = By.xpath(
        "//*[contains(text(),'Select my store') or " +
        "contains(text(),'Find a Store') or " +
        "contains(text(),'Store Locator')]"
    );
 
    private By cookieAcceptButton = By.id("onetrust-accept-btn-handler");
 
    // ===== CONSTRUCTOR =====
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
 
    // ===== HANDLE COOKIE POPUP =====
    public void acceptCookiesIfPresent() {
        try {
            WebElement cookieBtn = wait.until(
                ExpectedConditions.elementToBeClickable(cookieAcceptButton)
            );
            cookieBtn.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieAcceptButton));
            System.out.println("[INFO] Cookie popup accepted.");
        } catch (Exception e) {
            System.out.println("[INFO] Cookie popup not present or already handled.");
        }
    }
 
    // ===== VALIDATION =====
    public boolean isFindStoreLinkDisplayed() {
        try {
            WebElement findStore = wait.until(
                ExpectedConditions.visibilityOfElementLocated(findStoreButton)
            );
            return findStore.isDisplayed();
        } catch (Exception e) {
            System.out.println("[WARN] 'Find a Store' link not visible: " + e.getMessage());
            return false;
        }
    }
 
    // ===== CLICK FIND STORE =====
    public void clickFindStore() {
        // FIX #2: Split into two separate try/catch blocks so you know exactly
        // which step failed — the button click or the popup appearance.
        WebElement findStore;
        try {
            findStore = wait.until(ExpectedConditions.elementToBeClickable(findStoreButton));
            safeClick(findStore);
            System.out.println("[INFO] Clicked 'Find a Store' button.");
        } catch (TimeoutException e) {
            throw new RuntimeException(
                "'Find a Store' button not clickable after 30s. " +
                "URL: " + driver.getCurrentUrl() +
                " | Title: " + driver.getTitle(), e
            );
        }
 
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreText));
            System.out.println("[INFO] Store locator popup opened successfully.");
        } catch (TimeoutException e) {
            throw new RuntimeException(
                "Store locator popup did not appear after clicking 'Find a Store'. " +
                "URL: " + driver.getCurrentUrl(), e
            );
        }
    }
 
    // ===== POPUP VISIBILITY CHECK =====
    // FIX #4: This method only checks the popup opened — it does NOT drive
    // interactions inside the popup. Use StoreLocatorHelper for that.
    public boolean isSelectMyStoreLinkDisplayed() {
        try {
            WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(selectMyStoreText)
            );
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.println("[WARN] 'Select My Store' text not visible: " + e.getMessage());
            return false;
        }
    }
 
    public void clickSelectMyStore() {
        try {
            WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(selectMyStoreText)
            );
            safeClick(element);
            System.out.println("[INFO] Clicked 'Select My Store'.");
        } catch (TimeoutException e) {
            throw new RuntimeException(
                "Failed to click 'Select My Store'. " +
                "URL: " + driver.getCurrentUrl(), e
            );
        }
    }
 
    // ===== UTIL =====
    // FIX #3: Catch only ElementClickInterceptedException on the primary click.
    // This prevents silent fallthrough to JS click when the element is genuinely
    // missing or stale — those should surface as real errors.
    private void safeClick(WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("[INFO] Click intercepted, falling back to JS click.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
