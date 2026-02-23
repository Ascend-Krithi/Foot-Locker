package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By findStoreHeader = By.linkText("Find a Store");
    private By findStoreHeaderFallback1 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreHeaderFallback2 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void open(String url) {
        driver.get(url);
    }

    public void clickFindAStore() {
        if (BrowserUtils.isDisplayed(driver, findStoreHeader)) {
            BrowserUtils.click(driver, findStoreHeader);
        } else if (BrowserUtils.isDisplayed(driver, findStoreHeaderFallback1)) {
            BrowserUtils.click(driver, findStoreHeaderFallback1);
        } else {
            BrowserUtils.click(driver, findStoreHeaderFallback2);
        }
    }
}
