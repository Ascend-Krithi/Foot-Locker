package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreLocatorPage {
    private WebDriver driver;

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
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

    public void enterLocation(String location) {
        WebElement input = getSearchInput();
        BrowserUtils.typeAndEnter(input, location);
    }

    public boolean isSearchInputPresent() {
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
        // Assume the search button is next to the input, or use a generic button
        try {
            WebElement input = getSearchInput();
            WebElement parent = input.findElement(By.xpath(".."));
            List<WebElement> buttons = parent.findElements(By.tagName("button"));
            for (WebElement btn : buttons) {
                if (btn.isDisplayed() && btn.isEnabled()) {
                    return true;
                }
            }
        } catch (Exception ignored) {}
        return false;
    }

    public void clickSearchButton() {
        WebElement input = getSearchInput();
        WebElement parent = input.findElement(By.xpath(".."));
        List<WebElement> buttons = parent.findElements(By.tagName("button"));
        for (WebElement btn : buttons) {
            if (btn.isDisplayed() && btn.isEnabled()) {
                btn.click();
                return;
            }
        }
        throw new RuntimeException("Search button not found next to search input");
    }
}
