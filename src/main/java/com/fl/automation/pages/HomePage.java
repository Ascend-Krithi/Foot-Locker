package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    private final By findAStoreLink = By.linkText("Find a Store");
    private final By findAStorePopup = By.xpath("//div[contains(@class,'popover') or contains(@class,'popup') or contains(@class,'modal')][.//a[contains(.,'Select My Store')]]");
    private final By popupMessage = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");
    private final By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void open() {
        driver.get(ConfigReader.get("base.url"));
    }

    public void clickFindAStore() {
        BrowserUtils.click(driver, findAStoreLink);
    }

    public boolean isFindAStorePopupDisplayed() {
        return BrowserUtils.isDisplayed(driver, findAStorePopup);
    }

    public boolean isPopupMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, popupMessage);
    }

    public boolean isSelectMyStoreLinkDisplayed() {
        return BrowserUtils.isDisplayed(driver, selectMyStoreLink);
    }

    public void clickSelectMyStore() {
        BrowserUtils.click(driver, selectMyStoreLink);
    }
}
