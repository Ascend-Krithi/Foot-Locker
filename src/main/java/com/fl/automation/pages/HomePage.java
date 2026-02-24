package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By findStoreHeader = By.linkText("Find a Store");
    private By findStoreHeaderCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreHeaderXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isFindStoreVisible() {
        return BrowserUtils.isDisplayed(driver, findStoreHeader)
                || BrowserUtils.isDisplayed(driver, findStoreHeaderCss)
                || BrowserUtils.isDisplayed(driver, findStoreHeaderXpath);
    }

    public void clickFindStore() {
        if (BrowserUtils.isDisplayed(driver, findStoreHeader)) {
            BrowserUtils.click(driver, findStoreHeader);
        } else if (BrowserUtils.isDisplayed(driver, findStoreHeaderCss)) {
            BrowserUtils.click(driver, findStoreHeaderCss);
        } else {
            BrowserUtils.click(driver, findStoreHeaderXpath);
        }
    }
}
