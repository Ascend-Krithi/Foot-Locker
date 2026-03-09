package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private final WebDriver driver;
    private final By searchInput = By.cssSelector("input[type='search']");
    private final By searchInputFallback1 = By.cssSelector("input[name='q']");
    private final By searchInputFallback2 = By.cssSelector("input[aria-label*='Search']");
    private final By selectMyStore = By.xpath("//a[contains(.,'Select My Store')]");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public void enterSearch(String text) {
        if (BrowserUtils.isDisplayed(driver, searchInput)) {
            BrowserUtils.sendKeys(driver, searchInput, text);
        } else if (BrowserUtils.isDisplayed(driver, searchInputFallback1)) {
            BrowserUtils.sendKeys(driver, searchInputFallback1, text);
        } else {
            BrowserUtils.sendKeys(driver, searchInputFallback2, text);
        }
    }

    public void clickSelectMyStore() {
        BrowserUtils.click(driver, selectMyStore);
    }
}
