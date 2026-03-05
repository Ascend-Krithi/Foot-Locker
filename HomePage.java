package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private final By findStoreLink = By.linkText("Find a Store");
    private final By findStoreLinkCss = By.cssSelector("header a[href*='stores.footlocker.com']");
    private final By findStoreLinkXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void launch() {
        driver.get(ConfigReader.get("base.url"));
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("footlocker.com");
    }

    public void clickFindStore() {
        if (BrowserUtils.isDisplayed(driver, findStoreLink)) {
            BrowserUtils.click(driver, findStoreLink);
        } else if (BrowserUtils.isDisplayed(driver, findStoreLinkCss)) {
            BrowserUtils.click(driver, findStoreLinkCss);
        } else {
            BrowserUtils.click(driver, findStoreLinkXpath);
        }
    }
}
