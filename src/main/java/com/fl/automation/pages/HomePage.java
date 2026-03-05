package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private final By findStoreLink = By.linkText("Find a Store");
    private final By findStoreLinkFallback1 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private final By findStoreLinkFallback2 = By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String baseUrl) {
        driver.get(baseUrl);
    }

    public void clickFindAStore() {
        By[] locators = new By[]{findStoreLink, findStoreLinkFallback1, findStoreLinkFallback2};
        for (By locator : locators) {
            if (BrowserUtils.isDisplayed(driver, locator)) {
                BrowserUtils.click(driver, locator);
                return;
            }
        }
        throw new RuntimeException("Find a Store link not found");
    }
}
