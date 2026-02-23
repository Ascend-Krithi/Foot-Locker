package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private final WebDriver driver;
    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }
    private WebElement getSelectMyStoreLink() {
        try {
            return driver.findElement(By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"));
        } catch (Exception e1) {
            return driver.findElement(By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]") );
        }
    }
    public void clickSelectMyStore() {
        BrowserUtils.click(getSelectMyStoreLink(), driver);
    }
    public boolean isPopupDisplayed() {
        try {
            WebElement popup = getSelectMyStoreLink();
            return BrowserUtils.isDisplayed(popup, driver);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isPopupMessageDisplayed() {
        return driver.getPageSource().contains("Choose a preferred store to make shopping easier");
    }
    public boolean isSelectMyStoreLinkVisible() {
        try {
            return BrowserUtils.isDisplayed(getSelectMyStoreLink(), driver);
        } catch (Exception e) {
            return false;
        }
    }
}
