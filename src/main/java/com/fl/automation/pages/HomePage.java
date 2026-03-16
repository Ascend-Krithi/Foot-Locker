package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final List<By> FIND_STORE_LOCATORS = Arrays.asList(
            By.linkText("Find a Store"),
            By.cssSelector("header a[href*='stores.footlocker.com']"),
            By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    );

    private static final List<By> SELECT_MY_STORE_LOCATORS = Arrays.asList(
            By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store')]"),
            By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store')]")
    );

    private static final List<By> COOKIE_ACCEPT_LOCATORS = Arrays.asList(
            By.id("onetrust-accept-btn-handler"),
            By.cssSelector("button#onetrust-accept-btn-handler"),
            By.cssSelector("button[aria-label*='Accept' i]")
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        handleCookieConsent();
    }

    private void handleCookieConsent() {
        try {
            for (By locator : COOKIE_ACCEPT_LOCATORS) {
                try {
                    WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(locator));
                    clickElement(cookieButton);
                    Thread.sleep(1000);
                    return;
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {
            // Cookie banner not present or already accepted
        }
    }

    public void clickFindStore() {
        WebElement findStoreLink = findElementWithFallback(FIND_STORE_LOCATORS);
        wait.until(ExpectedConditions.elementToBeClickable(findStoreLink));
        clickElement(findStoreLink);
    }

    public boolean isStorePopupMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Select My Store') or contains(text(),'Set My Store') or contains(text(),'Find a Store')]"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            WebElement selectMyStoreLink = findElementWithFallback(SELECT_MY_STORE_LOCATORS);
            return wait.until(ExpectedConditions.visibilityOf(selectMyStoreLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        WebElement selectMyStoreLink = findElementWithFallback(SELECT_MY_STORE_LOCATORS);
        wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreLink));
        clickElement(selectMyStoreLink);
    }

    private WebElement findElementWithFallback(List<By> locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }

    private void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}