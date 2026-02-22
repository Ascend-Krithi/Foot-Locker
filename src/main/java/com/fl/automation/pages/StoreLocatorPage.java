package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.WaitUtils;

public class StoreLocatorPage {
    private WebDriver driver;
    private By searchBox = By.id("store-search-box");
    private By searchButton = By.id("store-search-btn");
    private By results = By.id("store-results");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchStore(String storeName) {
        WebElement box = driver.findElement(searchBox);
        WaitUtils.waitForVisibility(driver, box, 10);
        box.clear();
        box.sendKeys(storeName);
        driver.findElement(searchButton).click();
    }

    public boolean isResultDisplayed() {
        WebElement result = driver.findElement(results);
        WaitUtils.waitForVisibility(driver, result, 10);
        return result.isDisplayed();
    }
}
