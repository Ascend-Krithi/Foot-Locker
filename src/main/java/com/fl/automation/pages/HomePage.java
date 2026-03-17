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

    private By findStoreButton = By.xpath(
        "//a[contains(@href,'store-locator')] | " +
        "//a[contains(normalize-space(),'Find a Store')] | " +
        "//span[contains(normalize-space(),'Find a Store')] | " +
        "//button[contains(normalize-space(),'Find a Store')]"
    );

    private By storeDropdown = By.xpath(
        "//*[contains(text(),'Choose a preferred store') or " +
        "contains(text(),'Select my store') or " +
        "contains(text(),'Find a Store')]"
    );

    private By selectMyStoreLink = By.xpath(
        "//a[contains(normalize-space(),'Select my store')] | " +
        "//button[contains(normalize-space(),'Select my store')] | " +
        "//*[contains(@class,'store') and contains(normalize-space(),'Select my store')]"
    );

    // ✅ Fixed: target the modal OVERLAY/DIALOG — not the header nav text
    private By findAStoreModal = By.xpath(
        "//*[contains(@class,'modal') or contains(@class,'dialog') or contains(@class,'overlay') or contains(@class,'flyout') or contains(@class,'drawer')" +
        " or contains(@class,'StoreLocator') or contains(@class,'store-locator') or contains(@class,'storelocator')]"
    );

    private By cookieAcceptButton = By.id("onetrust-accept-btn-handler");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

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

    public void clickFindStore() {
        try {
            WebElement findStore = wait.until(
                ExpectedConditions.elementToBeClickable(findStoreButton)
            );
            safeClick(findStore);
            System.out.println("[INFO] Clicked 'Find a Store' button.");
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeDropdown));
            System.out.println("[INFO] Store locator dropdown is visible.");
        } catch (TimeoutException e) {
            throw new RuntimeException(
                "'Find a Store' button not clickable or dropdown did not appear. " +
                "URL: " + driver.getCurrentUrl(), e
            );
        }
    }

    public boolean isFindStoreLinkDisplayed() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(findStoreButton)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkDisplayed() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(selectMyStoreLink)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        try {
            WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(selectMyStoreLink)
            );
            safeClick(element);
            System.out.println("[INFO] Clicked 'Select my store' link.");

            // ✅ Log all classes on page to detect what the modal container looks like
            logModalCandidates();

            // ✅ Wait for modal container to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(findAStoreModal));
            System.out.println("[INFO] 'Find a Store' modal opened successfully.");

        } catch (TimeoutException e) {
            throw new RuntimeException(
                "Failed to open 'Find a Store' modal. URL: " + driver.getCurrentUrl(), e
            );
        }
    }

    // ✅ Diagnostic: log what modal-like containers exist after clicking Select my store
    private void logModalCandidates() {
        try {
            Thread.sleep(2000); // brief wait for modal to start rendering
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String script =
                "var candidates = [];" +
                "document.querySelectorAll('*').forEach(function(el) {" +
                "  var cls = el.className;" +
                "  if (typeof cls === 'string' && (" +
                "    cls.toLowerCase().includes('modal') || " +
                "    cls.toLowerCase().includes('dialog') || " +
                "    cls.toLowerCase().includes('overlay') || " +
                "    cls.toLowerCase().includes('drawer') || " +
                "    cls.toLowerCase().includes('flyout') || " +
                "    cls.toLowerCase().includes('store') || " +
                "    cls.toLowerCase().includes('locator')" +
                "  )) {" +
                "    candidates.push(el.tagName + ' | class=' + cls.substring(0,80));" +
                "  }" +
                "});" +
                "return candidates.slice(0,20);";
            @SuppressWarnings("unchecked")
            java.util.List<String> results = (java.util.List<String>) js.executeScript(script);
            System.out.println("[DEBUG] Modal candidates found: " + results.size());
            for (String r : results) {
                System.out.println("[DEBUG] Candidate: " + r);
            }
        } catch (Exception e) {
            System.out.println("[WARN] Could not log modal candidates: " + e.getMessage());
        }
    }

    private void safeClick(WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("[INFO] Click intercepted, falling back to JS click.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
