package com.fl.automation.pages;

import org.openqa.selenium.By;
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
    private WebDriverWait shortWait;

    private By[] cookieAcceptCandidates = new By[]{
        By.id("onetrust-accept-btn-handler"),
        By.cssSelector("button#onetrust-accept-btn-handler"),
        By.cssSelector("button[aria-label*='Accept' i]")
    };

    private By[] modalCloseCandidates = new By[]{
        By.cssSelector("button[aria-label='Close'], button[aria-label*='close' i]"),
        By.xpath("//button[@aria-label='Close' or contains(@class,'close') or contains(@data-action,'close')]")
    };

    private By[] findAStoreCandidates = new By[]{
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]"),
        By.xpath("//*[normalize-space()='Find a Store' or normalize-space()='Find a store']")
    };

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement cookieButton = findElementWithFallback(cookieAcceptCandidates, shortWait);
            if (cookieButton != null) {
                clickWithJsFallback(cookieButton);
            }
        } catch (Exception e) {
            // Cookie banner not present or already accepted
        }
    }

    public void closeModalIfPresent() {
        try {
            WebElement closeButton = findElementWithFallback(modalCloseCandidates, shortWait);
            if (closeButton != null) {
                clickWithJsFallback(closeButton);
            }
        } catch (Exception e) {
            // Modal not present
        }
    }

    public void clickFindAStore() {
        acceptCookiesIfPresent();
        closeModalIfPresent();
        
        WebElement findStoreElement = findElementWithFallback(findAStoreCandidates, wait);
        if (findStoreElement != null) {
            clickWithJsFallback(findStoreElement);
        } else {
            throw new RuntimeException("Find a Store element not found after trying all locator candidates");
        }
    }

    private WebElement findElementWithFallback(By[] candidates, WebDriverWait waitInstance) {
        for (By locator : candidates) {
            try {
                return waitInstance.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (TimeoutException e) {
                continue;
            }
        }
        return null;
    }

    private void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}