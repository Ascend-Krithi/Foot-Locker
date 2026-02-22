package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private final WebDriver driver;

    private final By popupMessageLocator = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");
    private final By[] selectMyStoreLocators = new By[] {
            By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]") ,
            By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isPopupMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, popupMessageLocator);
    }

    public String getPopupMessageText() {
        if (isPopupMessageDisplayed()) {
            return BrowserUtils.getText(driver, popupMessageLocator);
        }
        return null;
    }

    public boolean isSelectMyStoreLinkVisible() {
        for (By locator : selectMyStoreLocators) {
            if (BrowserUtils.isDisplayed(driver, locator)) {
                return true;
            }
        }
        return false;
    }
}
