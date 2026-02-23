package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private WebDriver driver;
    // Strict locator policy
    private final By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By popupMessage = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");
    private final By locationInput = By.cssSelector("input[type='search']");
    private final By locationInputAlt1 = By.cssSelector("input[name='q']");
    private final By locationInputAlt2 = By.cssSelector("input[aria-label*='Search']");
    private final By locationInputAlt3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private final By searchButton = By.xpath("//button[contains(.,'Search for Stores')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPopupMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, popupMessage);
    }

    public boolean isSelectMyStoreLinkVisible() {
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
                BrowserUtils.isDisplayed(driver, locationInputAlt1) ||
                BrowserUtils.isDisplayed(driver, locationInputAlt2) ||
                BrowserUtils.isDisplayed(driver, locationInputAlt3);
    }

    public void enterLocation(String location) {
        if (BrowserUtils.isDisplayed(driver, locationInput)) {
            BrowserUtils.type(driver, locationInput, location);
        } else if (BrowserUtils.isDisplayed(driver, locationInputAlt1)) {
            BrowserUtils.type(driver, locationInputAlt1, location);
        } else if (BrowserUtils.isDisplayed(driver, locationInputAlt2)) {
            BrowserUtils.type(driver, locationInputAlt2, location);
        } else {
            BrowserUtils.type(driver, locationInputAlt3, location);
        }
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, searchButton);
    }
}
