package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private final WebDriver driver;
    private final By searchInput1 = By.cssSelector("input[type='search']");
    private final By searchInput2 = By.cssSelector("input[name='q']");
    private final By searchInput3 = By.cssSelector("input[aria-label*='Search']");
    private final By searchInput4 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private final By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By emptyResultsMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreLocatorPage() {
        this.driver = DriverFactory.getDriver();
    }

    public void enterSearchText(String text) {
        if (BrowserUtils.isDisplayed(driver, searchInput1)) {
            BrowserUtils.type(driver, searchInput1, text);
        } else if (BrowserUtils.isDisplayed(driver, searchInput2)) {
            BrowserUtils.type(driver, searchInput2, text);
        } else if (BrowserUtils.isDisplayed(driver, searchInput3)) {
            BrowserUtils.type(driver, searchInput3, text);
        } else {
            BrowserUtils.type(driver, searchInput4, text);
        }
    }

    public boolean isStoreResultDisplayed() {
        return BrowserUtils.isDisplayed(driver, storeResultCards);
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMsg);
    }
}
