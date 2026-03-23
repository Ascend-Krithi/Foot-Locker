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
    
    // Locators
    private By cookieAcceptButton = By.id("onetrust-accept-btn-handler");
    private By findStoreButton = By.xpath("//button[contains(@class,'FindStore') or contains(text(),'Find') or contains(@aria-label,'store')]|//a[contains(@href,'store')]|//*[@data-testid='find-store']");
    private By selectMyStoreButton = By.xpath("//button[contains(text(),'Select') or contains(@class,'SelectStore')]|//a[contains(text(),'Select')]|//*[@data-testid='select-store']");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    public void acceptCookiesIfPresent() {
        try {
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            cookieBtn.click();
        } catch (Exception e) {
            // Cookie popup may not appear
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
}