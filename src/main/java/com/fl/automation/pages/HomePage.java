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

    public void clickFindStore() {
        WebElement el = null;
        if (BrowserUtils.isDisplayed(driver, driver.findElement(findStoreHeader))) {
            el = driver.findElement(findStoreHeader);
        } else if (BrowserUtils.isDisplayed(driver, driver.findElement(findStoreHeaderFallback1))) {
            el = driver.findElement(findStoreHeaderFallback1);
        } else {
            el = driver.findElement(findStoreHeaderFallback2);
        }
        BrowserUtils.click(driver, el);
    }

    public boolean isFindStoreVisible() {
        try {
            if (BrowserUtils.isDisplayed(driver, driver.findElement(findStoreHeader))) return true;
            if (BrowserUtils.isDisplayed(driver, driver.findElement(findStoreHeaderFallback1))) return true;
            if (BrowserUtils.isDisplayed(driver, driver.findElement(findStoreHeaderFallback2))) return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
