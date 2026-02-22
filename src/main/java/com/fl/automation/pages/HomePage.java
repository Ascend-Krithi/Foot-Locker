package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private By findStoreLink1 = By.linkText("Find a Store");
    private By findStoreLink2 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreLink3 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        try {
            BrowserUtils.click(driver, findStoreLink1);
        } catch (Exception e1) {
            try {
                BrowserUtils.click(driver, findStoreLink2);
            } catch (Exception e2) {
                BrowserUtils.click(driver, findStoreLink3);
            }
        }
    }

    public boolean isFindAStoreDisplayed() {
        try {
            return BrowserUtils.isDisplayed(driver, findStoreLink1);
        } catch (Exception e1) {
            try {
                return BrowserUtils.isDisplayed(driver, findStoreLink2);
            } catch (Exception e2) {
                return BrowserUtils.isDisplayed(driver, findStoreLink3);
            }
        }
    }
}