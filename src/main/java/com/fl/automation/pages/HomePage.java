package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private By findStoreLink = By.linkText("Find a Store");
    private By findStoreLinkCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreLinkXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        try {
            BrowserUtils.click(driver, findStoreLink);
        } catch (Exception e1) {
            try {
                BrowserUtils.click(driver, findStoreLinkCss);
            } catch (Exception e2) {
                BrowserUtils.click(driver, findStoreLinkXpath);
            }
        }
    }

    public boolean isFindAStoreDisplayed() {
        try {
            return BrowserUtils.isDisplayed(driver, findStoreLink);
        } catch (Exception e1) {
            try {
                return BrowserUtils.isDisplayed(driver, findStoreLinkCss);
            } catch (Exception e2) {
                return BrowserUtils.isDisplayed(driver, findStoreLinkXpath);
            }
        }
    }
}