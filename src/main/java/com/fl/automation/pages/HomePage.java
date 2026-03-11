package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By[] findStoreHeaderLocators = new By[] {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    private By[] selectMyStoreLocators = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private By storePopup = By.xpath("//div[contains(@class,'StoreLocator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void clickFindStore() {
        WebElement element = null;
        for (By locator : findStoreHeaderLocators) {
            try {
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                break;
            } catch (Exception e) {
                continue;
            }
        }
        if (element != null) {
            try {
                element.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
        }
    }

    public boolean isStorePopupDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(storePopup)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            for (By locator : selectMyStoreLocators) {
                try {
                    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                    if (element.isDisplayed()) {
                        return true;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        WebElement element = null;
        for (By locator : selectMyStoreLocators) {
            try {
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                break;
            } catch (Exception e) {
                continue;
            }
        }
        if (element != null) {
            try {
                element.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
        }
    }
}