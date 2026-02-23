package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private By findAStoreLink = By.linkText("Find a Store");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        BrowserUtils.click(driver, findAStoreLink);
    }

    public boolean isFindAStoreDisplayed() {
        return BrowserUtils.isDisplayed(driver, findAStoreLink);
    }
}