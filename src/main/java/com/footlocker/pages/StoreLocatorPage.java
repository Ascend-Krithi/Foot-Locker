package com.footlocker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private WebDriver driver;
    private By searchInput = By.id("store-search-input");
    private By storeResultCards = By.className("store-result-card");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForStore(String query) {
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(query);
        driver.findElement(searchInput).submit();
    }

    public boolean isStoreResultDisplayed() {
        return !driver.findElements(storeResultCards).isEmpty();
    }
}