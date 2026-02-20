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
                WebElement el = WaitUtils.waitForDisplayed(driver, locator);
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
                WebElement el = WaitUtils.waitForDisplayed(driver, locator);
                if (el != null && el.isDisplayed() && el.isEnabled()) {
                    return el;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Search input not found using KB locator fallbacks");
    }
    public boolean isPopupMessageDisplayed(String message) {
        return driver.getPageSource().contains(message);
    }
    public boolean isLocationTextboxPresent() {
        for (By locator : KBLocators.SEARCH_INPUT) {
            try {
                WebElement el = WaitUtils.waitForDisplayed(driver, locator);
                if (el != null && el.isDisplayed()) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }
    public boolean isSearchButtonPresent() {
        // Search button is usually next to input, try to find a button in the same form
        for (By locator : KBLocators.SEARCH_INPUT) {
            try {
                WebElement input = driver.findElement(locator);
                WebElement form = input.findElement(By.xpath("ancestor::form"));
                WebElement btn = form.findElement(By.xpath(".//button"));
                if (btn.isDisplayed() && btn.isEnabled()) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }
    public void enterLocation(String location) {
        WebElement input = getSearchInput();
        input.clear();
        input.sendKeys(location);
    }
    public void submitSearch() {
        for (By locator : KBLocators.SEARCH_INPUT) {
            try {
                WebElement input = driver.findElement(locator);
                input.sendKeys("\n");
                return;
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Unable to submit search using KB locator fallbacks");
    }
}
