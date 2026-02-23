package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.BrowserUtils;

public class StoreLocatorPage {
    private WebDriver driver;
    private static final By[] SELECT_MY_STORE_LOCATORS = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')"]),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };
    private static final By[] SEARCH_INPUT_LOCATORS = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };
    private static final By SEARCH_BUTTON = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMyStore() {
        for (By locator : SELECT_MY_STORE_LOCATORS) {
            if (BrowserUtils.isDisplayed(driver, locator)) {
                BrowserUtils.click(driver, locator);
                return;
            }
        }
        throw new RuntimeException("Select My Store link/button not found");
    }

    public boolean isLocationTextboxDisplayed() {
        for (By locator : SEARCH_INPUT_LOCATORS) {
            if (BrowserUtils.isDisplayed(driver, locator)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSearchButtonDisplayed() {
        return BrowserUtils.isDisplayed(driver, SEARCH_BUTTON);
    }

    public boolean isSearchButtonEnabled() {
        try {
            return driver.findElement(SEARCH_BUTTON).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        for (By locator : SEARCH_INPUT_LOCATORS) {
            if (BrowserUtils.isDisplayed(driver, locator)) {
                BrowserUtils.type(driver, locator, location);
                return;
            }
        }
        throw new RuntimeException("Search input not found");
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, SEARCH_BUTTON);
    }
}
