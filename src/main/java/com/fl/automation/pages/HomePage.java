package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    private By findStoreHeader = By.linkText("Find a Store");
    private By findStoreHeaderFallback1 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreHeaderFallback2 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        WebElement element;
        try {
            element = WaitUtils.waitForElementVisible(driver, findStoreHeader);
        } catch (Exception e1) {
            try {
                element = WaitUtils.waitForElementVisible(driver, findStoreHeaderFallback1);
            } catch (Exception e2) {
                element = WaitUtils.waitForElementVisible(driver, findStoreHeaderFallback2);
            }
        }
        BrowserUtils.click(element);
    }
}
