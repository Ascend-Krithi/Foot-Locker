package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private WebDriver driver;

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSelectMyStoreElement() {
        for (By locator : KBLocators.SELECT_MY_STORE) {
            try {
                WebElement el = WaitUtils.waitForDisplayed(driver, locator);
                if (el != null && el.isDisplayed() && el.isEnabled()) return el;
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Select My Store element not found using KB locator strategy");
    }

    public void clickSelectMyStore() {
        getSelectMyStoreElement().click();
    }

    public WebElement getSearchInput() {
        for (By locator : KBLocators.SEARCH_INPUT) {
            try {
                WebElement el = WaitUtils.waitForDisplayed(driver, locator);
                if (el != null && el.isDisplayed() && el.isEnabled()) return el;
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Search input not found using KB locator strategy");
    }

    public void enterSearchLocation(String location) {
        WebElement input = getSearchInput();
        input.clear();
        input.sendKeys(location);
    }

    public void submitSearch() {
        WebElement input = getSearchInput();
        input.sendKeys("\n");
    }
}
