package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.BrowserUtils;

public class HomePage {
    private WebDriver driver;

    private By findAStoreLocator1 = By.linkText("Find a Store");
    private By findAStoreLocator2 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findAStoreLocator3 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        if (BrowserUtils.isDisplayed(driver, findAStoreLocator1)) {
            BrowserUtils.click(driver, findAStoreLocator1);
        } else if (BrowserUtils.isDisplayed(driver, findAStoreLocator2)) {
            BrowserUtils.click(driver, findAStoreLocator2);
        } else if (BrowserUtils.isDisplayed(driver, findAStoreLocator3)) {
            BrowserUtils.click(driver, findAStoreLocator3);
        } else {
            throw new RuntimeException("Find a Store link not found");
        }
    }
}