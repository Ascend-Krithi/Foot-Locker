package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.BrowserUtils;

public class StoreLocatorPage {
    private WebDriver driver;

    private By selectMyStoreLocator1 = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreLocator2 = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMyStore() {
        if (BrowserUtils.isDisplayed(driver, selectMyStoreLocator1)) {
            BrowserUtils.click(driver, selectMyStoreLocator1);
        } else if (BrowserUtils.isDisplayed(driver, selectMyStoreLocator2)) {
            BrowserUtils.click(driver, selectMyStoreLocator2);
        } else {
            throw new RuntimeException("Select My Store element not found");
        }
    }
}