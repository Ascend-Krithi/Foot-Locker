package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class StoreLocatorPage {
    private WebDriver driver;

    // Locators
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButtonFallback = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    private By locationInput = By.cssSelector("input[type='search']");
    private By locationInputFallback1 = By.cssSelector("input[name='q']");
    private By locationInputFallback2 = By.cssSelector("input[aria-label*='Search']");
    private By locationInputFallback3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");

    private By searchButton = By.xpath("//button[contains(.,'Search for Stores')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPopupDisplayed() {
        // Assume popup is visible if Select My Store link/button is displayed
        return BrowserUtils.isDisplayed(driver, driver.findElement(selectMyStoreLink)) ||
               BrowserUtils.isDisplayed(driver, driver.findElement(selectMyStoreButtonFallback));
    }

    public boolean isSelectMyStoreLinkVisible() {
        return BrowserUtils.isDisplayed(driver, driver.findElement(selectMyStoreLink)) ||
               BrowserUtils.isDisplayed(driver, driver.findElement(selectMyStoreButtonFallback));
    }

    public void clickSelectMyStore() {
        if (BrowserUtils.isDisplayed(driver, driver.findElement(selectMyStoreLink))) {
            BrowserUtils.click(driver, driver.findElement(selectMyStoreLink));
        } else {
            BrowserUtils.click(driver, driver.findElement(selectMyStoreButtonFallback));
        }
    }

    public WebElement getLocationInput() {
        if (BrowserUtils.isDisplayed(driver, driver.findElement(locationInput))) {
            return driver.findElement(locationInput);
        } else if (BrowserUtils.isDisplayed(driver, driver.findElement(locationInputFallback1))) {
            return driver.findElement(locationInputFallback1);
        } else if (BrowserUtils.isDisplayed(driver, driver.findElement(locationInputFallback2))) {
            return driver.findElement(locationInputFallback2);
        } else {
            return driver.findElement(locationInputFallback3);
        }
    }

    public boolean isLocationInputVisible() {
        return BrowserUtils.isDisplayed(driver, getLocationInput());
    }

    public boolean isSearchButtonVisible() {
        try {
            return BrowserUtils.isDisplayed(driver, driver.findElement(searchButton));
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        BrowserUtils.type(driver, getLocationInput(), location);
    }

    public void clickSearchForStores() {
        BrowserUtils.click(driver, driver.findElement(searchButton));
    }
}
