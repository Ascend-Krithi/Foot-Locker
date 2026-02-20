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
    public WebElement getSelectMyStoreLink() {
        for (By locator : KBLocators.SELECT_SET_MY_STORE) {
            try {
                WebElement el = WaitUtils.waitForElementDisplayed(driver, locator);
                if (el != null && el.isDisplayed() && el.isEnabled()) return el;
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Select/Set My Store link/button not found using KB locator fallbacks");
    }
    public void clickSelectMyStore() {
        getSelectMyStoreLink().click();
    }
    public WebElement getSearchInput() {
        for (By locator : KBLocators.SEARCH_INPUT) {
            try {
                WebElement el = WaitUtils.waitForElementDisplayed(driver, locator);
                if (el != null && el.isDisplayed() && el.isEnabled()) return el;
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Search input not found using KB locator fallbacks");
    }
    public void enterLocation(String location) {
        WebElement input = getSearchInput();
        input.clear();
        input.sendKeys(location);
    }
    public boolean isSearchInputPresent() {
        try {
            getSearchInput();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isSelectMyStorePresent() {
        try {
            getSelectMyStoreLink();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
