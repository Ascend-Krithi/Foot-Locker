package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    private By locationInput = By.id("StoreLocator_search_query");
    private By searchButton = By.xpath("//button[@type='submit']");
    private By storeResultsContainer = By.xpath("//div[contains(@class,'store-card')]//div[contains(@class,'StoreCard')]//div[contains(@class,'store-details')]//div[contains(@class,'StoreDetails')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterLocation(String location) {
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(locationInput));
        input.clear();
        input.sendKeys(location);
    }

    public void clickSearch() {
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        search.click();
    }

    public boolean isStoreDisplayed(String partialAddress) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(storeResultsContainer));
            WebElement storeResult = driver.findElement(storeResultsContainer);
            return storeResult.getText().contains(partialAddress);
        } catch (Exception e) {
            return false;
        }
    }
}