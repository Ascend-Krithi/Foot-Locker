package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private static final By FIND_STORE_LINK = By.linkText("Find a Store");
    private static final By FIND_STORE_LINK_FALLBACK1 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private static final By FIND_STORE_LINK_FALLBACK2 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        try {
            BrowserUtils.click(driver, FIND_STORE_LINK);
        } catch (Exception e1) {
            try {
                BrowserUtils.click(driver, FIND_STORE_LINK_FALLBACK1);
            } catch (Exception e2) {
                BrowserUtils.click(driver, FIND_STORE_LINK_FALLBACK2);
            }
        }
    }

    public boolean isFindAStoreDisplayed() {
        try {
            return BrowserUtils.isDisplayed(driver, FIND_STORE_LINK);
        } catch (Exception e1) {
            try {
                return BrowserUtils.isDisplayed(driver, FIND_STORE_LINK_FALLBACK1);
            } catch (Exception e2) {
                return BrowserUtils.isDisplayed(driver, FIND_STORE_LINK_FALLBACK2);
            }
        }
    }
}