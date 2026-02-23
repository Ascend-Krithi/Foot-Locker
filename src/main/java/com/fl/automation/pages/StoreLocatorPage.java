package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private WebDriver driver;
    private By popupMessage = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButtonFallback = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By locationInput = By.cssSelector("input[type='search']");
    private By locationInputFallback1 = By.cssSelector("input[name='q']");
    private By locationInputFallback2 = By.cssSelector("input[aria-label*='Search']");
    private By locationInputFallback3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By searchButton = By.xpath("//button[contains(.,'Search for Stores')]");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isPopupMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, popupMessage);
    }

    public boolean isSelectMyStoreLinkVisible() {
        return BrowserUtils.isDisplayed(driver, selectMyStoreLink) || BrowserUtils.isDisplayed(driver, selectMyStoreButtonFallback);
    }

    public void clickSelectMyStore() {
        if (BrowserUtils.isDisplayed(driver, selectMyStoreLink)) {
            BrowserUtils.click(driver, selectMyStoreLink);
        } else {
            BrowserUtils.click(driver, selectMyStoreButtonFallback);
        }
    }

    public boolean isLocationInputVisible() {
        return BrowserUtils.isDisplayed(driver, locationInput)
                || BrowserUtils.isDisplayed(driver, locationInputFallback1)
                || BrowserUtils.isDisplayed(driver, locationInputFallback2)
                || BrowserUtils.isDisplayed(driver, locationInputFallback3);
    }

    public void enterLocation(String location) {
        if (BrowserUtils.isDisplayed(driver, locationInput)) {
            BrowserUtils.type(driver, locationInput, location);
        } else if (BrowserUtils.isDisplayed(driver, locationInputFallback1)) {
            BrowserUtils.type(driver, locationInputFallback1, location);
        } else if (BrowserUtils.isDisplayed(driver, locationInputFallback2)) {
            BrowserUtils.type(driver, locationInputFallback2, location);
        } else {
            BrowserUtils.type(driver, locationInputFallback3, location);
        }
    }

    public void clickSearchForStores() {
        BrowserUtils.click(driver, searchButton);
    }
}
