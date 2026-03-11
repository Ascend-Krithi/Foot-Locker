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

    private By findStoreLinkText = By.linkText("Find a Store");
    private By findStoreHeaderLink = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreXPath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    private By selectMyStoreXPath1 = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreXPath2 = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void clickFindStore() {
        WebElement element = null;
        try {
            element = wait.until(ExpectedConditions.elementToBeClickable(findStoreLinkText));
        } catch (Exception e1) {
            try {
                element = wait.until(ExpectedConditions.elementToBeClickable(findStoreHeaderLink));
            } catch (Exception e2) {
                element = wait.until(ExpectedConditions.elementToBeClickable(findStoreXPath));
            }
        }
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public boolean isStorePopupDisplayed() {
        try {
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'StoreLocatorPopup') or contains(text(),'Choose a preferred store')]"));
            return popup.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            WebElement link = null;
            try {
                link = wait.until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreXPath1));
            } catch (Exception e) {
                link = wait.until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreXPath2));
            }
            return link.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        WebElement element = null;
        try {
            element = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreXPath1));
        } catch (Exception e) {
            element = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreXPath2));
        }
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}