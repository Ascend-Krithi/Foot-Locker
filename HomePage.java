package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.BrowserUtils;

public class HomePage {
    private WebDriver driver;
    private static final By[] FIND_STORE_HEADER_LOCATORS = new By[] {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        for (By locator : FIND_STORE_HEADER_LOCATORS) {
            if (BrowserUtils.isDisplayed(driver, locator)) {
                BrowserUtils.click(driver, locator);
                return;
            }
        }
        throw new RuntimeException("Find a Store link not found");
    }
}
