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

    // ✅ StoreLocatorDropdown input — confirmed from debug logs
    private By storeLocatorDropdownInput = By.xpath(
        "//*[contains(@class,'StoreLocatorDropdown')]//input"
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

            // ✅ Wait for StoreLocatorDropdown input to appear (no modal — it's a dropdown)
            wait.until(ExpectedConditions.presenceOfElementLocated(storeLocatorDropdownInput));
            System.out.println("[INFO] 'Find a Store' dropdown expanded successfully.");

        } catch (TimeoutException e) {
            throw new RuntimeException(
                "StoreLocatorDropdown did not expand after clicking 'Select my store'. " +
                "URL: " + driver.getCurrentUrl(), e
            );
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
