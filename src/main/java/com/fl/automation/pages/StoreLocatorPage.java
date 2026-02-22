package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private WebDriver driver;

    private By selectMyStoreLink1 = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButton1 = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMyStore() {
        try {
            BrowserUtils.click(driver, selectMyStoreLink1);
        } catch (Exception e) {
            BrowserUtils.click(driver, selectMyStoreButton1);
        }
    }

    public boolean isSelectMyStoreDisplayed() {
        if (BrowserUtils.isDisplayed(driver, selectMyStoreLink1)) {
            return true;
        } else {
            return BrowserUtils.isDisplayed(driver, selectMyStoreButton1);
        }
    }
}