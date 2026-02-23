package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    // Strict locator policy for Find a Store header
    private final By findStoreHeader = By.linkText("Find a Store");
    private final By findStoreHeaderAlt1 = By.cssSelector("header a[href*=stores.footlocker.com]");
    private final By findStoreHeaderAlt2 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");
    private final By popupMessage = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");
    private final By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void launch() {
        driver.get(ConfigReader.get("baseUrl"));
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

    public boolean isPopupMessageDisplayed() {
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
