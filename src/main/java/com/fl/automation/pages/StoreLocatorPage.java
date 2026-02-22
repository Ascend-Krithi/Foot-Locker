package com.fl.automation.pages;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.WaitUtils;
import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private WebDriver driver;
    private By popupLocator = By.xpath("//div[contains(@class,'popover') or contains(@class,'popup') or contains(@class,'store')][.//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPopupMessage() {
        WebElement popup = driver.findElement(popupLocator);
        WaitUtils.waitForVisibility(driver, popup);
        return BrowserUtils.getText(popup);
    }

    private WebElement getSelectMyStoreLink() {
        try {
            return driver.findElement(By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"));
        } catch (Exception e) {
            return driver.findElement(By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"));
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        WebElement link = getSelectMyStoreLink();
        return BrowserUtils.isDisplayed(link);
    }
}