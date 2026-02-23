package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.BrowserUtils;

public class StoreLocatorPage {
    private WebDriver driver;
    public static final By SELECT_MY_STORE_XPATH1 = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    public static final By SELECT_MY_STORE_XPATH2 = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMyStore() {
        if (BrowserUtils.isDisplayed(driver, SELECT_MY_STORE_XPATH1)) {
            BrowserUtils.click(driver, SELECT_MY_STORE_XPATH1);
        } else if (BrowserUtils.isDisplayed(driver, SELECT_MY_STORE_XPATH2)) {
            BrowserUtils.click(driver, SELECT_MY_STORE_XPATH2);
        } else {
            throw new RuntimeException("Select My Store button/link not found");
        }
    }

    public boolean isSelectMyStoreDisplayed() {
        return BrowserUtils.isDisplayed(driver, SELECT_MY_STORE_XPATH1) || BrowserUtils.isDisplayed(driver, SELECT_MY_STORE_XPATH2);
    }
}