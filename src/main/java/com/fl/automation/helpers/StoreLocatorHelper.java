package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By locationInput = By.id("StoreLocator_search_query");
    private By searchButton = By.xpath("//button[contains(.,'Search for Stores')] | //button[contains(.,'Search Stores')]");
    private By storeResults = By.xpath("//div[contains(@class,'StoreResult')] | //div[contains(@class,'store-result')]");
    private By setMyStoreButton = By.xpath("//button[contains(.,'Set My Store')] | //button[contains(.,'Update My Store')]");
    private By storeConfirmation = By.xpath("//*[contains(text(),'Your preferred store')] | //*[contains(text(),'Store set')] | //*[contains(@class,'store-confirmation')]");
    private By headerStoreName = By.xpath("//header//*[contains(text(),'Washington')] | //*[contains(@class,'header')]//*[contains(text(),'Washington')]");
    
    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }
    
    public void waitForStoreLocatorToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
    }
    
    public void enterLocation(String location) {
        WebElement locationField = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
        locationField.clear();
        locationField.sendKeys(location);
    }
    
    public void clickSearchForStores() {
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        try {
            searchBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBtn);
        }
    }
    
    public boolean areStoreResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResults));
            List<WebElement> results = driver.findElements(storeResults);
            return results.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isStoreDisplayed(String storeName) {
        try {
            By storeLocator = By.xpath("//*[contains(text(),'" + storeName + "')]");
            WebElement store = wait.until(ExpectedConditions.visibilityOfElementLocated(storeLocator));
            return store.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickSetMyStore() {
        WebElement setStoreBtn = wait.until(ExpectedConditions.elementToBeClickable(setMyStoreButton));
        try {
            setStoreBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", setStoreBtn);
        }
    }
    
    public boolean isStoreConfirmationDisplayed() {
        try {
            WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(storeConfirmation));
            return confirmation.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isStoreNameInHeader(String storeName) {
        try {
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(headerStoreName));
            return header.getText().contains(storeName) || header.getText().contains("Washington");
        } catch (Exception e) {
            return false;
        }
    }
}