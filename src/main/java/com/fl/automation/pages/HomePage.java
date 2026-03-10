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

    private By[] findStoreHeaderLocators = {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void navigateToHomePage(String url) {
        driver.get(url);
        waitForPageLoad();
    }

    public boolean isHomePageLoaded() {
        try {
            wait.until(ExpectedConditions.titleContains("Foot Locker"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickFindAStore() {
        WebElement element = waitForFirstVisible(findStoreHeaderLocators);
        clickWithFallback(element);
    }

    public boolean isFindAStorePopupVisible() {
        By[] popupLocators = {
            By.xpath("//*[contains(.,'Choose a preferred store')]"),
            By.xpath("//*[contains(.,'Select My Store')]"),
            By.cssSelector("[class*='store-popup']"),
            By.cssSelector("[class*='store-locator']"),
            By.xpath("//div[contains(@class,'popup') or contains(@class,'modal')]//*[contains(.,'Store')]"),
            By.xpath("//*[contains(.,'make shopping easier')]"),
            By.xpath("//*[contains(.,'preferred store')]"),
            By.cssSelector("div[role='dialog']")
        };
        
        for (By locator : popupLocators) {
            try {
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));
                WebElement element = shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                if (element.isDisplayed()) {
                    return true;
                }
            } catch (TimeoutException e) {
                continue;
            }
        }
        return false;
    }

    public boolean isSelectMyStoreLinkVisible() {
        By[] selectMyStoreLocators = {
            By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
            By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
        };
        
        for (By locator : selectMyStoreLocators) {
            try {
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));
                WebElement element = shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                if (element.isDisplayed()) {
                    return true;
                }
            } catch (TimeoutException e) {
                continue;
            }
        }
        return false;
    }

    private WebElement waitForFirstVisible(By[] locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (TimeoutException e) {
                continue;
            }
        }
        throw new RuntimeException("None of the fallback locators found element");
    }

    private void clickWithFallback(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private void waitForPageLoad() {
        try {
            wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        } catch (Exception e) {
            System.out.println("Page load wait timed out");
        }
    }
}
