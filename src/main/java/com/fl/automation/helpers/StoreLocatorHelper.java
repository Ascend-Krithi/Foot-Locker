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
    
    // Locators
    private By searchInput = By.xpath("//input[contains(@id,'StoreLocator_search') or contains(@placeholder,'location') or contains(@name,'search')]|//*[@data-testid='store-search-input']");
    private By searchButton = By.xpath("//button[contains(@class,'SearchButton') or contains(text(),'Search')]|//button[@type='submit']|//*[@data-testid='store-search-button']");
    private By storeResultsContainer = By.xpath("//*[contains(@class,'store-card') or contains(@class,'StoreCard') or contains(@class,'store-details') or contains(@class,'StoreDetails') or contains(@class,'store-result') or contains(@class,'StoreResult')]");
    
    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    public void enterLocation(String location) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(location);
    }
    
    public void clickSearchButton() {
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        try {
            searchBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBtn);
        }
    }
    
    public boolean isStoreDisplayed(String partialAddress) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResultsContainer));
            List<WebElement> stores = driver.findElements(storeResultsContainer);
            for (WebElement store : stores) {
                if (store.getText().contains(partialAddress)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}