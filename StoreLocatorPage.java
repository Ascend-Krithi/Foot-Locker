package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private WebDriver driver;
    private final By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By locationInput = By.cssSelector("input[type='search']");
    private final By locationInputName = By.cssSelector("input[name='q']
");
    private final By locationInputAria = By.cssSelector("input[aria-label*='Search']");
    private final By locationInputPlaceholder = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private final By searchButton = By.xpath("//button[contains(.,'Search for Stores')]");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
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

    public boolean isLocationInputPresent() {
        return BrowserUtils.isDisplayed(driver, locationInput) ||
                BrowserUtils.isDisplayed(driver, locationInputName) ||
                BrowserUtils.isDisplayed(driver, locationInputAria) ||
                BrowserUtils.isDisplayed(driver, locationInputPlaceholder);
    }

    public void enterLocation(String location) {
        if (BrowserUtils.isDisplayed(driver, locationInput)) {
            BrowserUtils.type(driver, locationInput, location);
        } else if (BrowserUtils.isDisplayed(driver, locationInputName)) {
            BrowserUtils.type(driver, locationInputName, location);
        } else if (BrowserUtils.isDisplayed(driver, locationInputAria)) {
            BrowserUtils.type(driver, locationInputAria, location);
        } else {
            BrowserUtils.type(driver, locationInputPlaceholder, location);
        }
    }

    public boolean isSearchButtonPresent() {
        return BrowserUtils.isDisplayed(driver, searchButton);
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, searchButton);
    }
}
