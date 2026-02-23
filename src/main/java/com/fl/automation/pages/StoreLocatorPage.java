package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.utils.BrowserUtils;

public class StoreLocatorPage {
    private WebDriver driver;
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMyStore() {
        WebElement selectMyStore = driver.findElement(selectMyStoreLink);
        BrowserUtils.click(driver, selectMyStore);
    }
}