package com.fl.automation.pages;

import com.fl.automation.utils.WaitUtils;
import com.fl.automation.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private WebDriver driver;
    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSearchInput() {
        for (By locator : KBLocators.SEARCH_INPUT) {
            try {
                return WaitUtils.waitForElementDisplayed(driver, locator);
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Search input not found using KB locator fallbacks");
    }

    public void enterLocation(String location) {
        WebElement input = getSearchInput();
        BrowserUtils.typeAndEnter(input, location);
    }

    public void searchLocation(String location) {
        WebElement input = getSearchInput();
        input.clear();
        input.sendKeys(location);
        input.submit();
    }
}
