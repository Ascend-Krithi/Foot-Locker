package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;

    private final By[] findStoreLocators = new By[] {
            By.linkText("Find a Store"),
            By.cssSelector("header a[href*='stores.footlocker.com']"),
            By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
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
        throw new RuntimeException("Find a Store button not found using strict locator policy.");
    }
}
