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
        for (By locator : KBLocators.SELECT_MY_STORE) {
            try {
                WebElement el = WaitUtils.waitForElementDisplayed(driver, locator);
                if (el != null && el.isDisplayed() && el.isEnabled()) {
                    return el;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Select My Store link/button not found using KB locator fallbacks");
    }

    public WebElement getSearchInput() {
        for (By locator : KBLocators.SEARCH_INPUT) {
            try {
                WebElement el = WaitUtils.waitForElementDisplayed(driver, locator);
                if (el != null && el.isDisplayed() && el.isEnabled()) {
                    return el;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Search input not found using KB locator fallbacks");
    }

    public boolean isPopupMessageDisplayed(String message) {
        for (By locator : KBLocators.SELECT_MY_STORE) {
            try {
                WebElement el = WaitUtils.waitForElementDisplayed(driver, locator);
                if (el != null && el.getText().contains(message)) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }
}
