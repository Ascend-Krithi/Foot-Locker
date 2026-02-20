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
    public WebElement getSearchButton() {
        // Try to find a button next to the search input
        WebElement input = getSearchInput();
        try {
            WebElement parent = input.findElement(By.xpath(".."));
            WebElement btn = parent.findElement(By.xpath(".//button"));
            if (btn.isDisplayed() && btn.isEnabled()) {
                return btn;
            }
        } catch (Exception ignored) {}
        // Fallback: try to find a button in the popup/modal
        try {
            WebElement btn = driver.findElement(By.xpath("//button[contains(.,'Search') or contains(.,'Find') or contains(.,'Go') or contains(.,'Submit') or contains(.,'Store') or contains(.,'Stores') or contains(.,'SEARCH') or contains(.,'FIND') or contains(.,'GO') or contains(.,'SUBMIT') or contains(.,'STORE') or contains(.,'STORES') ]"));
            if (btn.isDisplayed() && btn.isEnabled()) {
                return btn;
            }
        } catch (Exception ignored) {}
        throw new RuntimeException("Search button not found");
    }
}
