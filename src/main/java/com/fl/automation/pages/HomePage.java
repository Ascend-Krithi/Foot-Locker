package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class HomePage {
    private WebDriver driver;
    private By findStoreHeader = By.linkText("Find a Store");
    private By findStoreHeaderFallback1 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreHeaderFallback2 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        WebElement el = null;
        try {
            el = driver.findElement(findStoreHeader);
            if (BrowserUtils.isDisplayed(driver, el)) {
                BrowserUtils.click(driver, el);
                return;
            }
        } catch (Exception e) {}
        try {
            el = driver.findElement(findStoreHeaderFallback1);
            if (BrowserUtils.isDisplayed(driver, el)) {
                BrowserUtils.click(driver, el);
                return;
            }
        } catch (Exception e) {}
        el = driver.findElement(findStoreHeaderFallback2);
        BrowserUtils.click(driver, el);
    }

    public boolean isFindStoreVisible() {
        try {
            WebElement el = driver.findElement(findStoreHeader);
            if (BrowserUtils.isDisplayed(driver, el)) return true;
        } catch (Exception e) {}
        try {
            WebElement el = driver.findElement(findStoreHeaderFallback1);
            if (BrowserUtils.isDisplayed(driver, el)) return true;
        } catch (Exception e) {}
        try {
            WebElement el = driver.findElement(findStoreHeaderFallback2);
            if (BrowserUtils.isDisplayed(driver, el)) return true;
        } catch (Exception e) {}
        return false;
    }
}
