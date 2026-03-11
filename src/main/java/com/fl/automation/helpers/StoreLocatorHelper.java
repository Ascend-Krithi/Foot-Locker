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
    
    private By searchInput = By.cssSelector("input[type='search']");
    private By searchInput_fallback1 = By.cssSelector("input[name='q']");
    private By searchInput_fallback2 = By.cssSelector("input[aria-label*='Search']");
    private By searchInput_fallback3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    
    private By searchButton = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search') or contains(.,'Find Stores')]");
    
    private By locationTextbox = By.cssSelector("input[placeholder*='Location' i], input[placeholder*='City' i], input[placeholder*='ZIP' i], input[aria-label*='Location' i]");
    
    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");
    
    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }
    
    public boolean isLocationTextboxVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSearchButtonVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void enterLocation(String location) {
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
            input.clear();
            input.sendKeys(location);
        } catch (Exception e1) {
            try {
                WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput_fallback1));
                input.clear();
                input.sendKeys(location);
            } catch (Exception e2) {
                try {
                    WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput_fallback2));
                    input.clear();
                    input.sendKeys(location);
                } catch (Exception e3) {
                    WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput_fallback3));
                    input.clear();
                    input.sendKeys(location);
                }
            }
        }
    }
    
    public void clickSearchButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(searchButton);
            js.executeScript("arguments[0].click();", element);
        }
    }
    
    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(storeResultCards));
            return results.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public int getStoreResultCount() {
        try {
            List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(storeResultCards));
            return results.size();
        } catch (Exception e) {
            return 0;
        }
    }
}
