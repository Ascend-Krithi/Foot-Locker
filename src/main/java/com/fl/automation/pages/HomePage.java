package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    private final By findStoreHeaderLink = By.linkText("Find a Store");
    private final By findStoreHeaderCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private final By findStoreHeaderXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void open() {
        driver.get(ConfigReader.getProperty("base.url"));
    }

    public boolean isFindStoreHeaderDisplayed() {
        return BrowserUtils.isDisplayed(driver, findStoreHeaderLink)
                || BrowserUtils.isDisplayed(driver, findStoreHeaderCss)
                || BrowserUtils.isDisplayed(driver, findStoreHeaderXpath);
    }

    public void clickFindStoreHeader() {
        if (BrowserUtils.isDisplayed(driver, findStoreHeaderLink)) {
            BrowserUtils.click(driver, findStoreHeaderLink);
        } else if (BrowserUtils.isDisplayed(driver, findStoreHeaderCss)) {
            BrowserUtils.click(driver, findStoreHeaderCss);
        } else {
            BrowserUtils.click(driver, findStoreHeaderXpath);
        }
    }
}
