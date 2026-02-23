package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;
    private final By findStoreHeader = By.linkText("Find a Store");
    private final By findStoreHeaderFallback1 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private final By findStoreHeaderFallback2 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo() {
        driver.get(com.fl.automation.core.ConfigReader.get("base.url"));
    }

    public void clickFindAStore() {
        WebElement el;
        try {
            el = WaitUtils.waitForElementVisible(driver, findStoreHeader);
        } catch (Exception e1) {
            try {
                el = WaitUtils.waitForElementVisible(driver, findStoreHeaderFallback1);
            } catch (Exception e2) {
                el = WaitUtils.waitForElementVisible(driver, findStoreHeaderFallback2);
            }
        }
        com.fl.automation.core.BrowserUtils.click(el);
    }
}