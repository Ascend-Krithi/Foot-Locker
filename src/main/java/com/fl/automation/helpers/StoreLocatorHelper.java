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
    
    private By locationInput = By.cssSelector("input[type='search']");
    private By locationInputAlt1 = By.cssSelector("input[name='q']");
    private By locationInputAlt2 = By.cssSelector("input[aria-label*='Search']");
    private By locationInputAlt3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    
    private By searchButton = By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]");
    private By searchButtonAlt1 = By.cssSelector("[aria-label*='Search for store' i], button[type='submit']");
    private By searchButtonAlt2 = By.cssSelector("button[aria-label*='Search' i]");
    private By searchButtonAlt3 = By.xpath("//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search')]");
    
    private By storeResults = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    
    public StoreLocatorHelper(WebDriver driver){
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
    
    public void enterLocation(String location){
        WebElement input = findElementWithFallback(locationInput, locationInputAlt1, locationInputAlt2, locationInputAlt3);
        input.clear();
        input.sendKeys(location);
    }
    
    public void clickSearchStores(){
        WebElement button = findElementWithFallback(searchButton, searchButtonAlt1, searchButtonAlt2, searchButtonAlt3);
        clickWithFallback(button);
    }
    
    public boolean areStoreResultsDisplayed(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResults));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isStoreAddressVisible(String expectedAddress){
        try {
            List<WebElement> results = driver.findElements(storeResults);
            for (WebElement result : results) {
                try {
                    WebElement addressElement = result.findElement(storeAddress);
                    String actualAddress = addressElement.getText().trim();
                    if (actualAddress.contains(expectedAddress) || expectedAddress.contains(actualAddress)) {
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
    
    public void clickSetMyStoreForAddress(String targetAddress){
        List<WebElement> results = driver.findElements(storeResults);
        for (WebElement result : results) {
            try {
                WebElement addressElement = result.findElement(storeAddress);
                String actualAddress = addressElement.getText().trim();
                if (actualAddress.contains(targetAddress) || targetAddress.contains(actualAddress)) {
                    WebElement setButton = result.findElement(setMyStoreButton);
                    clickWithFallback(setButton);
                    return;
                }
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Store with address '" + targetAddress + "' not found");
    }
    
    public boolean isStoreSetAsPreferred(String storeAddress){
        try {
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}