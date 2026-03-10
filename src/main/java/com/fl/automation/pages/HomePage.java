package com.fl.automation.pages;

import com.fl.automation.helpers.StoreLocatorHelper;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    private final StoreLocatorHelper storeLocatorHelper;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.storeLocatorHelper = new StoreLocatorHelper(driver);
    }

    public void clickFindAStore() {
        try {
            storeLocatorHelper.findStoreHeader().click();
        } catch (Exception e) {
            // JS click fallback
            storeLocatorHelper.findStoreHeader().getLocation();
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", storeLocatorHelper.findStoreHeader());
        }
    }

    public StoreLocatorHelper getStoreLocatorHelper() {
        return storeLocatorHelper;
    }
}
