package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private WebDriver driver;
    private final By popupMessage = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");
    private final By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By locationInput = By.cssSelector("input[type='search']");
    private final By locationInputFallback1 = By.cssSelector("input[name='q']");
    private final By locationInputFallback2 = By.cssSelector("input[aria-label*='Search']");
    private final By locationInputFallback3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private final By searchButton = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPopupDisplayed() {
        return BrowserUtils.isDisplayed(driver, popupMessage);
    }

    public boolean isSelectMyStoreLinkVisible() {
        return BrowserUtils.isDisplayed(driver, selectMyStoreLink) || BrowserUtils.isDisplayed(driver, selectMyStoreButton);
    }

    public void clickSelectMyStore() {
        if (BrowserUtils.isDisplayed(driver, selectMyStoreLink)) {
            BrowserUtils.click(driver, selectMyStoreLink);
        } else if (BrowserUtils.isDisplayed(driver, selectMyStoreButton)) {
            BrowserUtils.click(driver, selectMyStoreButton);
        } else {
            throw new RuntimeException("Select My Store link/button not found");
        }
    }

    public boolean isLocationInputPresent() {
        By[] locators = new By[]{locationInput, locationInputFallback1, locationInputFallback2, locationInputFallback3};
        for (By locator : locators) {
            if (BrowserUtils.isDisplayed(driver, locator)) {
                return true;
            }
        }
        return false;
    }

    public void enterLocation(String location) {
        By[] locators = new By[]{locationInput, locationInputFallback1, locationInputFallback2, locationInputFallback3};
        for (By locator : locators) {
            if (BrowserUtils.isDisplayed(driver, locator)) {
                BrowserUtils.type(driver, locator, location);
                return;
            }
        }
        throw new RuntimeException("Location input not found");
    }

    public boolean isSearchButtonPresent() {
        return BrowserUtils.isDisplayed(driver, searchButton);
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, searchButton);
    }
}
