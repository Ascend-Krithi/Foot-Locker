package com.fl.automation.pages;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private WebDriver driver;
    private By popupMessage = By.id("storeLocatorPopup");
    private By selectMyStoreLink = By.id("selectMyStoreLink");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isPopupMessageDisplayed() {
        WebElement popup = WaitUtils.waitForVisibility(driver.findElement(popupMessage), 10);
        return popup.isDisplayed();
    }

    public boolean isSelectMyStoreLinkVisible() {
        WebElement link = WaitUtils.waitForVisibility(driver.findElement(selectMyStoreLink), 10);
        return link.isDisplayed();
    }
}
