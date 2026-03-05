package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class HomePage {
    private WebDriver driver;

    private By findStoreHeaderLink = By.linkText("Find a Store");
    private By findStoreHeaderCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreHeaderXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isFindStoreHeaderDisplayed() {
        return BrowserUtils.isDisplayed(driver, driver.findElement(findStoreHeaderLink)) ||
               BrowserUtils.isDisplayed(driver, driver.findElement(findStoreHeaderCss)) ||
               BrowserUtils.isDisplayed(driver, driver.findElement(findStoreHeaderXpath));
    }

    public void clickFindStoreHeader() {
        if (BrowserUtils.isDisplayed(driver, driver.findElement(findStoreHeaderLink))) {
            BrowserUtils.click(driver, driver.findElement(findStoreHeaderLink));
        } else if (BrowserUtils.isDisplayed(driver, driver.findElement(findStoreHeaderCss))) {
            BrowserUtils.click(driver, driver.findElement(findStoreHeaderCss));
        } else {
            BrowserUtils.click(driver, driver.findElement(findStoreHeaderXpath));
        }
    }
}
