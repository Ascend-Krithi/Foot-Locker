package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.BrowserUtils;

public class StoreLocatorPage {
    private WebDriver driver;
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMyStore() {
        BrowserUtils.click(driver, selectMyStoreLink);
    }
}
