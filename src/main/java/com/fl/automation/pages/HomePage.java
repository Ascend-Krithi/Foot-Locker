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

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void clickFindAStore() {
        By[] candidates = new By[]{
            By.linkText("Find a Store"),
            By.cssSelector("header a[href*='stores.footlocker.com']"),
            By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
        };

        WebElement target = null;
        for (By by : candidates) {
            try {
                target = wait.until(ExpectedConditions.elementToBeClickable(by));
                break;
            } catch (TimeoutException ignored) {
            }
        }

        if (target == null) {
            throw new TimeoutException("Could not locate 'Find a Store' control using known locators.");
        }

        try {
            target.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", target);
        }
    }

    public boolean isFindAStorePopupDisplayed() {
        By popupBy = By.cssSelector("[role='dialog'], .modal, [class*='popup'], [class*='store-selector']");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(popupBy));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        By[] candidates = new By[]{
            By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
            By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
        };

        for (By by : candidates) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                if (element.isDisplayed()) {
                    return true;
                }
            } catch (TimeoutException ignored) {
            }
        }
        return false;
    }

    public boolean isPopupMessageDisplayed(String expectedMessage) {
        By messageBy = By.xpath("//*[contains(.,'" + expectedMessage + "')]");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(messageBy));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}