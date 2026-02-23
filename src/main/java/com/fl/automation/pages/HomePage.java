package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;
    private By findStoreLink = By.linkText("Find a Store");
    private By findStoreLinkCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreLinkXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isFindStoreVisible() {
        return BrowserUtils.isDisplayed(driver.findElement(findStoreLink)) ||
               BrowserUtils.isDisplayed(driver.findElement(findStoreLinkCss)) ||
               BrowserUtils.isDisplayed(driver.findElement(findStoreLinkXpath));
    }

    public void clickFindStore() {
        if (BrowserUtils.isDisplayed(driver.findElement(findStoreLink))) {
            BrowserUtils.click(driver.findElement(findStoreLink));
        } else if (BrowserUtils.isDisplayed(driver.findElement(findStoreLinkCss))) {
            BrowserUtils.click(driver.findElement(findStoreLinkCss));
        } else {
            BrowserUtils.click(driver.findElement(findStoreLinkXpath));
        }
    }
}
