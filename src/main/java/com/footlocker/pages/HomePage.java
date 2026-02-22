package com.footlocker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By findStoreHeader = By.xpath("//h1[contains(text(),'Find a Store')]");
    private By selectMyStore = By.id("select-my-store");
    private By searchInput = By.id("store-search-input");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isFindStoreHeaderDisplayed() {
        return driver.findElement(findStoreHeader).isDisplayed();
    }

    public void clickSelectMyStore() {
        driver.findElement(selectMyStore).click();
    }

    public void enterSearch(String query) {
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(query);
    }
}