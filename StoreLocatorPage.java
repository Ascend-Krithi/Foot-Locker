package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private final WebDriver driver;

    private final By choosePreferredStorePopup = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");
    private final By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By locationTextbox = By.cssSelector("input[type='search']");
    private final By locationTextboxAlt1 = By.cssSelector("input[name='q']");
    private final By locationTextboxAlt2 = By.cssSelector("input[aria-label*='Search']");
    private final By locationTextboxAlt3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private final By searchButton = By.cssSelector("button[type='submit']");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isChoosePreferredStorePopupDisplayed() {
        return BrowserUtils.isDisplayed(driver, choosePreferredStorePopup);
    }

    public boolean isSelectMyStoreLinkDisplayed() {
        return BrowserUtils.isDisplayed(driver, selectMyStoreLink) || BrowserUtils.isDisplayed(driver, selectMyStoreButton);
    }

    public void clickSelectMyStore() {
        if (BrowserUtils.isDisplayed(driver, selectMyStoreLink)) {
            BrowserUtils.click(driver, selectMyStoreLink);
        } else {
            BrowserUtils.click(driver, selectMyStoreButton);
        }
    }

    public boolean isLocationTextboxDisplayed() {
        return BrowserUtils.isDisplayed(driver, locationTextbox) ||
                BrowserUtils.isDisplayed(driver, locationTextboxAlt1) ||
                BrowserUtils.isDisplayed(driver, locationTextboxAlt2) ||
                BrowserUtils.isDisplayed(driver, locationTextboxAlt3);
    }

    public void enterLocation(String location) {
        if (BrowserUtils.isDisplayed(driver, locationTextbox)) {
            BrowserUtils.type(driver, locationTextbox, location);
        } else if (BrowserUtils.isDisplayed(driver, locationTextboxAlt1)) {
            BrowserUtils.type(driver, locationTextboxAlt1, location);
        } else if (BrowserUtils.isDisplayed(driver, locationTextboxAlt2)) {
            BrowserUtils.type(driver, locationTextboxAlt2, location);
        } else {
            BrowserUtils.type(driver, locationTextboxAlt3, location);
        }
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, searchButton);
    }
}
