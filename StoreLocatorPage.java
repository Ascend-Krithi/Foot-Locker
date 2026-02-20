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
                if (el != null && el.isDisplayed() && el.isEnabled()) return el;
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Select My Store link/button not found using KB locator fallbacks");
    }

    public void clickSelectMyStore() {
        getSelectMyStoreLink().click();
    }

    public WebElement getSearchInput() {
        for (By locator : KBLocators.SEARCH_INPUT) {
            try {
                WebElement el = WaitUtils.waitForDisplayed(driver, locator);
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

    public WebElement getSearchButton() {
        // Try to find a button next to the search input
        WebElement input = getSearchInput();
        WebElement parent = input.findElement(By.xpath(".."));
        try {
            WebElement btn = parent.findElement(By.xpath(".//button"));
            if (btn.isDisplayed() && btn.isEnabled()) return btn;
        } catch (Exception ignored) {}
        // fallback: try to find any visible button on the page
        for (WebElement btn : driver.findElements(By.tagName("button"))) {
            if (btn.isDisplayed() && btn.isEnabled() && btn.getText().toLowerCase().contains("search")) return btn;
        }
        throw new RuntimeException("Search for Stores button not found");
    }

    public void clickSearchButton() {
        getSearchButton().click();
    }
}
