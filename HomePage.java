package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By findStoreHeader = By.linkText("Find a Store");
    private By findStoreHeaderCss = By.cssSelector("header a[href*=stores.footlocker.com]");
    private By findStoreHeaderXpath = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");
    private By popupMessage = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void open() {
        driver.get(com.fl.automation.core.ConfigReader.get("base.url"));
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

    public boolean isFindStorePopupDisplayed() {
        return BrowserUtils.isDisplayed(driver, popupMessage);
    }

    public boolean isSelectMyStoreLinkDisplayed() {
        return BrowserUtils.isDisplayed(driver, selectMyStoreLink) || BrowserUtils.isDisplayed(driver, selectMyStoreButton);
    }

    public void clickSelectMyStore() {
        if (BrowserUtils.isDisplayed(driver, selectMyStoreLink)) {
            BrowserUtils.click(driver, selectMyStoreLink);
        } else {
            BrowserUtils.click(driver, selectMyStoreButton);
        }
    }
}
