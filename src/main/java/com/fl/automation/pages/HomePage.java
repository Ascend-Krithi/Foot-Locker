package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By findStoreLink = By.linkText("Find a Store");
    private By findStoreHeaderCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private By findStoreHeaderXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(ConfigReader.get("base.url"));
    }

    public void clickFindAStore() {
        if (BrowserUtils.isDisplayed(driver, findStoreLink)) {
            BrowserUtils.click(driver, findStoreLink);
        } else if (BrowserUtils.isDisplayed(driver, findStoreHeaderCss)) {
            BrowserUtils.click(driver, findStoreHeaderCss);
        } else {
            BrowserUtils.click(driver, findStoreHeaderXpath);
        }
    }
}
