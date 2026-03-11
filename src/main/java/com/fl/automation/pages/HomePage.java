package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By[] FIND_STORE_LOCATORS = {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    private final By[] COOKIE_CONSENT_LOCATORS = {
        By.id("onetrust-accept-btn-handler"),
        By.cssSelector("button#onetrust-accept-btn-handler"),
        By.xpath("//button[contains(.,'Accept All Cookies') or contains(.,'Accept Cookies')]")
    };

    private final By[] STORE_POPUP_MESSAGE_LOCATORS = {
        By.xpath("//*[contains(normalize-space(.),'Choose a preferred store to make shopping easier.') or contains(normalize-space(.),'Choose a preferred store')]"),
        By.cssSelector("div.store-popup"),
        By.xpath("//div[contains(@class,'popup') or contains(@class,'modal')]")
    };

    private final By[] SELECT_MY_STORE_LINK_LOCATORS = {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.linkText("Select My Store")
    };

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void dismissCookieConsent() {
        try {
            WebElement cookieButton = findFirstVisible(COOKIE_CONSENT_LOCATORS, 5);
            if (cookieButton != null) {
                try {
                    cookieButton.click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cookieButton);
                }
            }
        } catch (Exception e) {
        }
    }

    public void clickFindStore() {
        WebElement element = findFirstVisible(FIND_STORE_LOCATORS);
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public boolean isStorePopupDisplayed() {
        try {
            WebElement element = findFirstVisible(STORE_POPUP_MESSAGE_LOCATORS);
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            WebElement element = findFirstVisible(SELECT_MY_STORE_LINK_LOCATORS);
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        WebElement element = findFirstVisible(SELECT_MY_STORE_LINK_LOCATORS);
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private WebElement findFirstVisible(By[] locators) {
        return findFirstVisible(locators, 40);
    }

    private WebElement findFirstVisible(By[] locators, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        for (By locator : locators) {
            try {
                return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (TimeoutException e) {
            }
        }
        throw new NoSuchElementException("Required locator not provided in Locator Policy");
    }
}
