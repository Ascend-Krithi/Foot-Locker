package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By[] findStoreLocators = new By[] {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    private final By[] selectMyStoreLocators = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    private WebElement findElementWithFallback(By[] locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        throw new NoSuchElementException("Element not found with any of the provided locators");
    }

    private boolean isElementVisibleWithFallback(By[] locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }

    public void clickFindStore() {
        WebElement element = findElementWithFallback(findStoreLocators);
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public boolean isStorePopupDisplayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return isElementVisibleWithFallback(selectMyStoreLocators);
    }

    public boolean isSelectMyStoreLinkVisible() {
        return isElementVisibleWithFallback(selectMyStoreLocators);
    }

    public void clickSelectMyStore() {
        WebElement element = findElementWithFallback(selectMyStoreLocators);
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
