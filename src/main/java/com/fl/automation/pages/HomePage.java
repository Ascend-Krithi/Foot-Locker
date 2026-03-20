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
    
    private By cookieAcceptButton = By.id("onetrust-accept-btn-handler");
    private By findStoreButton = By.xpath("//a[normalize-space()='Find a Store'] | //button[normalize-space()='Find a Store'] | //a[contains(@class,'FindStore')]");
    private By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store')] | //a[contains(.,'Select My Store')]");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }
    
    public void acceptCookiesIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement cookieButton = shortWait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            try {
                cookieButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cookieButton);
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            // Cookie banner not present, continue
        }
    }
    
    public void clickFindStore() {
        WebElement findStore = wait.until(ExpectedConditions.elementToBeClickable(findStoreButton));
        try {
            findStore.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", findStore);
        }
    }
    
    public void clickSelectMyStore() {
        WebElement selectStore = wait.until(ExpectedConditions.elementToBeClickable(selectMyStoreButton));
        try {
            selectStore.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectStore);
        }
    }
    
    public boolean isFindStoreLinkDisplayed() {
        try {
            WebElement findStore = wait.until(ExpectedConditions.visibilityOfElementLocated(findStoreButton));
            return findStore.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isOnFootLockerDomain() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("footlocker.com");
    }
    
    public void navigateToSneakersPage() {
        driver.navigate().to("https://www.footlocker.com/category/mens/shoes/sneakers.html");
        wait.until(ExpectedConditions.urlContains("sneakers"));
    }
}