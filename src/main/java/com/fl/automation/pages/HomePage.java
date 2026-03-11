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
    
    private By findStoreLink = By.linkText("Find a Store");
    private By findStoreLink_fallback1 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreLink_fallback2 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");
    
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    
    private By popupMessage = By.xpath("//*[contains(.,'Choose a preferred store to make shopping easier')]");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }
    
    public void clickFindStore() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(findStoreLink)).click();
        } catch (Exception e1) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(findStoreLink_fallback1)).click();
            } catch (Exception e2) {
                try {
                    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(findStoreLink_fallback2));
                    element.click();
                } catch (Exception e3) {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    WebElement element = driver.findElement(findStoreLink_fallback2);
                    js.executeScript("arguments[0].click();", element);
                }
            }
        }
    }
    
    public boolean isPopupDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(popupMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSelectMyStoreLinkVisible() {
        try {
            WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreLink));
            return link.isDisplayed();
        } catch (Exception e) {
            try {
                WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(selectMyStoreButton));
                return button.isDisplayed();
            } catch (Exception e2) {
                return false;
            }
        }
    }
    
    public void clickSelectMyStore() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreLink)).click();
        } catch (Exception e1) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreButton)).click();
            } catch (Exception e2) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                try {
                    WebElement element = driver.findElement(selectMyStoreLink);
                    js.executeScript("arguments[0].click();", element);
                } catch (Exception e3) {
                    WebElement element = driver.findElement(selectMyStoreButton);
                    js.executeScript("arguments[0].click();", element);
                }
            }
        }
    }
}
