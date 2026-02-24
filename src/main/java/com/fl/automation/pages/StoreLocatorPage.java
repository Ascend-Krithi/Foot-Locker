package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private WebDriver driver;
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By locationInput1 = By.cssSelector("input[type='search']");
    private By locationInput2 = By.cssSelector("input[name='q']");
    private By locationInput3 = By.cssSelector("input[aria-label*='Search']");
    private By locationInput4 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By searchButton = By.xpath("//button[contains(.,'Search for Stores')]");
    private By popupMessage = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isPopupMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, popupMessage);
    }

    public boolean isSelectMyStoreVisible() {
        return BrowserUtils.isDisplayed(driver, selectMyStoreLink) || BrowserUtils.isDisplayed(driver, selectMyStoreButton);
    }

    public void clickSelectMyStore() {
        if (BrowserUtils.isDisplayed(driver, selectMyStoreLink)) {
            BrowserUtils.click(driver, selectMyStoreLink);
        } else {
            BrowserUtils.click(driver, selectMyStoreButton);
        }
    }

    public boolean isLocationInputVisible() {
        return BrowserUtils.isDisplayed(driver, locationInput1)
                || BrowserUtils.isDisplayed(driver, locationInput2)
                || BrowserUtils.isDisplayed(driver, locationInput3)
                || BrowserUtils.isDisplayed(driver, locationInput4);
    }

    public void enterLocation(String location) {
        if (BrowserUtils.isDisplayed(driver, locationInput1)) {
            BrowserUtils.type(driver, locationInput1, location);
        } else if (BrowserUtils.isDisplayed(driver, locationInput2)) {
            BrowserUtils.type(driver, locationInput2, location);
        } else if (BrowserUtils.isDisplayed(driver, locationInput3)) {
            BrowserUtils.type(driver, locationInput3, location);
        } else {
            BrowserUtils.type(driver, locationInput4, location);
        }
    }

    public void clickSearchForStores() {
        BrowserUtils.click(driver, searchButton);
    }
}
