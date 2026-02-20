package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreResultsPage {
    private WebDriver driver;
    private By storeResultCards = By.cssSelector("[data-qa=location], .c-location-card, .location, [class*=location-card]");
    private By storeAddress = By.cssSelector("[data-qa=address], .c-address, address, .address, [class*=address]");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isStoreResultsDisplayed() {
        return BrowserUtils.isDisplayed(driver, storeResultCards);
    }

    public boolean isStoreAddressDisplayed() {
        return BrowserUtils.isDisplayed(driver, storeAddress);
    }

    public boolean isSetMyStoreButtonDisplayed() {
        return BrowserUtils.isDisplayed(driver, setMyStoreButton);
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMessage);
    }
}
