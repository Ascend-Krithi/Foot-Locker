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

    // "Find a Store" button in the header
    private By findStoreButton = By.xpath(
        "//a[contains(@href,'store-locator')] | " +
        "//a[contains(normalize-space(),'Find a Store')] | " +
        "//span[contains(normalize-space(),'Find a Store')] | " +
        "//button[contains(normalize-space(),'Find a Store')]"
    );

    // The dropdown that appears after clicking "Find a Store"
    // Contains "Choose a preferred store to make shopping easier"
    private By storeDropdown = By.xpath(
        "//*[contains(text(),'Choose a preferred store') or " +
        "contains(text(),'Select my store') or " +
        "contains(text(),'Find a Store')]"
    );

    // CONFIRMED from screenshot: exact text is "Select my store" with arrow →
    // Targets the clickable link/button inside the dropdown
    private By selectMyStoreLink = By.xpath(
        "//a[contains(normalize-space(),'Select my store')] | " +
        "//button[contains(normalize-space(),'Select my store')] | " +
        "//*[contains(@class,'store') and contains(normalize-space(),'Select my store')]"
    );

    // The modal header — confirms modal opened
    private By findAStoreModalHeader = By.xpath(
        "//*[normalize-space()='Find a Store'] | " +
        "//*[contains(@class,'modal') and contains(text(),'Find')]"
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

    // ===== CLICK FIND STORE — opens the small dropdown =====
    public void clickFindStore() {
        try {
            WebElement findStore = wait.until(
                ExpectedConditions.elementToBeClickable(findStoreButton)
            );
            safeClick(findStore);
            System.out.println("[INFO] Clicked 'Find a Store' button.");

            // Wait for dropdown to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeDropdown));
            System.out.println("[INFO] Store locator dropdown is visible.");
        } catch (TimeoutException e) {
            throw new RuntimeException(
                "'Find a Store' button not clickable or dropdown did not appear. " +
                "URL: " + driver.getCurrentUrl() +
                " | Title: " + driver.getTitle(), e
            );
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

    // ===== CLICK SELECT MY STORE — opens the full Find a Store modal =====
    public boolean isSelectMyStoreLinkDisplayed() {
        try {
            WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(selectMyStoreLink)
            );
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.println("[WARN] 'Select My Store' link not visible: " + e.getMessage());
            return false;
        }
    }

    public void clickSelectMyStore() {
        try {
            // Wait for "Select my store" link in dropdown to be clickable
            WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(selectMyStoreLink)
            );
            safeClick(element);
            System.out.println("[INFO] Clicked 'Select my store' link.");

            // Wait for the full "Find a Store" modal to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(findAStoreModalHeader));
            System.out.println("[INFO] 'Find a Store' modal opened successfully.");
        } catch (TimeoutException e) {
            throw new RuntimeException(
                "Failed to open 'Find a Store' modal after clicking 'Select my store'. " +
                "URL: " + driver.getCurrentUrl(), e
            );
        }
    }

    // ===== UTIL =====
    private void safeClick(WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("[INFO] Click intercepted, falling back to JS click.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
