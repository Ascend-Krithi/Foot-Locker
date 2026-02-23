package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.BrowserUtils;

public class HomePage {
    private WebDriver driver;
    public static final By FIND_STORE_LINK = By.linkText("Find a Store");
    public static final By FIND_STORE_LINK_CSS = By.cssSelector("header a[href*='stores.footlocker.com']");
    public static final By FIND_STORE_LINK_XPATH = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        if (BrowserUtils.isDisplayed(driver, FIND_STORE_LINK)) {
            BrowserUtils.click(driver, FIND_STORE_LINK);
        } else if (BrowserUtils.isDisplayed(driver, FIND_STORE_LINK_CSS)) {
            BrowserUtils.click(driver, FIND_STORE_LINK_CSS);
        } else if (BrowserUtils.isDisplayed(driver, FIND_STORE_LINK_XPATH)) {
            BrowserUtils.click(driver, FIND_STORE_LINK_XPATH);
        } else {
            throw new RuntimeException("Find a Store link not found");
        }
    }
}