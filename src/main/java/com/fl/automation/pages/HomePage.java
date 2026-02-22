package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;

    // Strict locator policy for 'Find a Store' button
    private final By[] findStoreLocators = new By[] {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    // Popup message locator (assume visible after clicking Find a Store)
    private final By popupMessageLocator = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");

    // Strict locator policy for 'Select My Store' link
    private final By[] selectMyStoreLocators = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]") ,
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void clickFindAStore() {
        for (By locator : findStoreLocators) {
            if (BrowserUtils.isDisplayed(driver, locator)) {
                BrowserUtils.click(driver, locator);
                return;
            }
        }
        throw new RuntimeException("Find a Store button not found using strict locator policy");
    }

    public String getPopupMessage() {
        return BrowserUtils.getText(driver, popupMessageLocator);
    }

    public boolean isSelectMyStoreVisible() {
        for (By locator : selectMyStoreLocators) {
            if (BrowserUtils.isDisplayed(driver, locator)) {
                return true;
            }
        }
        return false;
    }
}
