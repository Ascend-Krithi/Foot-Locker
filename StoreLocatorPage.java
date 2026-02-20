package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import com.fl.automation.core.BrowserUtils;
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
                WebElement el = WaitUtils.waitForElementDisplayed(driver, locator);
                if (el != null && el.isDisplayed() && el.isEnabled()) {
                    return el;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Search input not found using KB locator fallbacks");
    }

    public void enterSearchLocation(String location) {
        WebElement input = getSearchInput();
        BrowserUtils.typeAndEnter(input, location);
    }

    public WebElement getSearchButton() {
        // Try to find a button next to the search input
        WebElement input = getSearchInput();
        WebElement parent = input.findElement(By.xpath(".."));
        try {
            WebElement btn = parent.findElement(By.xpath(".//button[contains(.,'Search') or contains(.,'Find') or contains(.,'Go') or contains(.,'Stores') or contains(.,'store') or contains(.,'Store') or contains(.,'SEARCH') or contains(.,'FIND') or contains(.,'GO') or contains(.,'STORES') or contains(.,'STORE') or contains(.,'search') or contains(.,'find') or contains(.,'go') or contains(.,'stores') or contains(.,'store') ]"));
            if (btn != null && btn.isDisplayed() && btn.isEnabled()) {
                return btn;
            }
        } catch (Exception ignored) {}
        throw new RuntimeException("Search button not found next to search input");
    }

    public void clickSearchButton() {
        WebElement btn = getSearchButton();
        BrowserUtils.jsClick(driver, btn);
    }
}
