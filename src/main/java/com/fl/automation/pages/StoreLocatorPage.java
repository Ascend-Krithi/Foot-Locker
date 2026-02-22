package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private final WebDriver driver;

    // STRICT LOCATOR POLICY: Use unique, stable locators only
    private final By popupMessage = By.cssSelector("div[data-test='store-locator-popup-message']");
    private final By selectMyStoreLink = By.cssSelector("a[data-test='select-my-store-link']");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public String getPopupMessage() {
        WebElement element = driver.findElement(popupMessage);
        return BrowserUtils.getText(driver, element);
    }

    public boolean isSelectMyStoreLinkVisible() {
        WebElement element = driver.findElement(selectMyStoreLink);
        return BrowserUtils.isDisplayed(driver, element);
    }
}
