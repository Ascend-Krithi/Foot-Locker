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
    
    private By cookieAccept = By.id("onetrust-accept-btn-handler");
    private By cookieAcceptAlt1 = By.cssSelector("button#onetrust-accept-btn-handler");
    private By cookieAcceptAlt2 = By.cssSelector("button[aria-label*='Accept' i]");
    private By cookieAcceptAlt3 = By.xpath("//button[contains(normalize-space(.),'Accept All Cookies') or contains(normalize-space(.),'Accept Cookies') or contains(normalize-space(.),'I Accept') or contains(normalize-space(.),'Accept All')]");
    
    private By modalClose = By.cssSelector("button[aria-label='Close'], button[aria-label*='close' i]");
    private By modalCloseAlt = By.xpath("//button[@aria-label='Close' or contains(@class,'close') or contains(@data-action,'close')]");
    
    private By findStoreLink = By.linkText("Find a Store");
    private By findStoreLinkAlt1 = By.xpath("//*[normalize-space()='Find a Store' or normalize-space()='Find a store']");
    private By findStoreLinkAlt2 = By.cssSelector("a[href*='store-locator'], a[aria-label*='Find a Store' i], button[aria-label*='Find a Store' i]");
    private By findStoreLinkAlt3 = By.xpath("//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'find a store')]");
    
    private By storePopup = By.xpath("//div[contains(.,'Choose a preferred store') or contains(.,'Select My Store')]");
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreLinkAlt = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    
    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }
    
    private WebElement findElementWithFallback(By... locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }
    
    private void clickWithFallback(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
    
    public void dismissCookieConsent(){
        try {
            WebElement acceptButton = findElementWithFallback(cookieAccept, cookieAcceptAlt1, cookieAcceptAlt2, cookieAcceptAlt3);
            clickWithFallback(acceptButton);
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }
    
    public void closeModalIfPresent(){
        try {
            WebElement closeButton = findElementWithFallback(modalClose, modalCloseAlt);
            clickWithFallback(closeButton);
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }
    
    public void clickFindStore(){
        WebElement findStore = findElementWithFallback(findStoreLink, findStoreLinkAlt1, findStoreLinkAlt2, findStoreLinkAlt3);
        clickWithFallback(findStore);
    }
    
    public boolean isStorePopupDisplayed(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storePopup));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSelectMyStoreLinkVisible(){
        try {
            WebElement link = findElementWithFallback(selectMyStoreLink, selectMyStoreLinkAlt);
            return link.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickSelectMyStore(){
        WebElement link = findElementWithFallback(selectMyStoreLink, selectMyStoreLinkAlt);
        clickWithFallback(link);
    }
}