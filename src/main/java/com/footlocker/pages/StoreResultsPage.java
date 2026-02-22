package com.footlocker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreResultsPage {
    private WebDriver driver;
    private By storeResultCards = By.className("store-result-card");
    private By storeAddress = By.className("store-address");
    private By setMyStoreButton = By.id("set-my-store-btn");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isStoreResultCardDisplayed() {
        return !driver.findElements(storeResultCards).isEmpty();
    }

    public String getFirstStoreAddress() {
        return driver.findElement(storeAddress).getText();
    }

    public void clickSetMyStore() {
        driver.findElement(setMyStoreButton).click();
    }
}