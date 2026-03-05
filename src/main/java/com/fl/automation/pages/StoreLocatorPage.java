package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {

    private WebDriver driver;

    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMyStore() {
        try {
            BrowserUtils.click(driver, selectMyStoreLink);
        } catch (Exception e) {
            BrowserUtils.click(driver, selectMyStoreButton);
        }
    }

    public boolean isSelectMyStoreDisplayed() {
        try {
            return BrowserUtils.isDisplayed(driver, selectMyStoreLink);
        } catch (Exception e) {
            return BrowserUtils.isDisplayed(driver, selectMyStoreButton);
        }
    }
}