package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private WebDriver driver;
    private By popupMessage = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPopupDisplayed() {
        return BrowserUtils.isDisplayed(driver, popupMessage);
    }

    public boolean isSelectMyStoreVisible() {
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
