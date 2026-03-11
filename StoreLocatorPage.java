package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class StoreLocatorPage {
    private WebDriver driver;

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
        try {
            return BrowserUtils.isDisplayed(driver, driver.findElement(selectMyStoreLink));
        } catch (Exception e) {
            try {
                return BrowserUtils.isDisplayed(driver, driver.findElement(selectMyStoreButtonFallback));
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            return BrowserUtils.isDisplayed(driver, driver.findElement(selectMyStoreLink));
        } catch (Exception e) {
            try {
                return BrowserUtils.isDisplayed(driver, driver.findElement(selectMyStoreButtonFallback));
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public void clickSelectMyStore() {
        try {
            BrowserUtils.click(driver, driver.findElement(selectMyStoreLink));
        } catch (Exception e) {
            BrowserUtils.click(driver, driver.findElement(selectMyStoreButtonFallback));
        }
    }

    public WebElement getLocationInput() {
        try {
            return driver.findElement(locationInput);
        } catch (Exception e1) {
            try {
                return driver.findElement(locationInputFallback1);
            } catch (Exception e2) {
                try {
                    return driver.findElement(locationInputFallback2);
                } catch (Exception e3) {
                    return driver.findElement(locationInputFallback3);
                }
            }
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
