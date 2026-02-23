package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    // Strict locator policy for 'Find a Store' header
    private final By findStoreHeader = By.linkText("Find a Store");
    private final By findStoreHeaderAlt1 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private final By findStoreHeaderAlt2 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void clickFindAStore() {
        if (BrowserUtils.isDisplayed(driver, findStoreHeader)) {
            BrowserUtils.click(driver, findStoreHeader);
        } else if (BrowserUtils.isDisplayed(driver, findStoreHeaderAlt1)) {
            BrowserUtils.click(driver, findStoreHeaderAlt1);
        } else {
            BrowserUtils.click(driver, findStoreHeaderAlt2);
        }
    }
}
