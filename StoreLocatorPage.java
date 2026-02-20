package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.DriverFactory;

public class StoreLocatorPage {
    private final WebDriver driver;

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public WebElement getSearchInput() {
        for (By locator : KBLocators.SEARCH_INPUT) {
            try {
                WebElement el = WaitUtils.waitForDisplayed(locator);
                if (el != null && el.isDisplayed()) {
                    return el;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Search input not found using KB locator fallbacks");
    }

    public void searchForStore(String location) {
        WebElement input = getSearchInput();
        BrowserUtils.typeAndEnter(input, location);
    }
}
