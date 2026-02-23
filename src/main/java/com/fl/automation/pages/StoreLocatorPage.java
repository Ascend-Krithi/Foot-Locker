package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private final WebDriver driver;
    private final By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By searchInput = By.cssSelector("input[type='search']");
    private final By searchInputName = By.cssSelector("input[name='q']");
    private final By searchInputAria = By.cssSelector("input[aria-label*='Search']");
    private final By searchInputPlaceholder = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private final By searchButton = By.cssSelector("button[type='submit'], button[aria-label*='Search']");
    private final By locationTextbox = searchInput;

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public void clickSelectMyStore() {
        if (BrowserUtils.isDisplayed(driver, selectMyStoreLink)) {
            BrowserUtils.click(driver, selectMyStoreLink);
        } else {
            BrowserUtils.click(driver, selectMyStoreButton);
        }
    }

    public boolean isLocationTextboxDisplayed() {
        return BrowserUtils.isDisplayed(driver, searchInput) ||
                BrowserUtils.isDisplayed(driver, searchInputName) ||
                BrowserUtils.isDisplayed(driver, searchInputAria) ||
                BrowserUtils.isDisplayed(driver, searchInputPlaceholder);
    }

    public boolean isSearchButtonDisplayed() {
        return BrowserUtils.isDisplayed(driver, searchButton);
    }

    public void enterLocation(String location) {
        if (BrowserUtils.isDisplayed(driver, searchInput)) {
            BrowserUtils.type(driver, searchInput, location);
        } else if (BrowserUtils.isDisplayed(driver, searchInputName)) {
            BrowserUtils.type(driver, searchInputName, location);
        } else if (BrowserUtils.isDisplayed(driver, searchInputAria)) {
            BrowserUtils.type(driver, searchInputAria, location);
        } else {
            BrowserUtils.type(driver, searchInputPlaceholder, location);
        }
    }

    public void clickSearch() {
        BrowserUtils.click(driver, searchButton);
    }

    public boolean isSearchButtonEnabled() {
        try {
            return driver.findElement(searchButton).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
}
