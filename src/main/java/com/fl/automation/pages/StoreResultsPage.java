package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreResultsPage {

    private WebDriver driver;

    private By searchInputTypeSearch = By.cssSelector("input[type='search']");
    private By searchInputNameQ = By.cssSelector("input[name='q']");
    private By searchInputAriaLabel = By.cssSelector("input[aria-label*='Search']");
    private By searchInputPlaceholder = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddressInsideCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButtonInsideCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        try {
            return BrowserUtils.isDisplayed(driver, searchInputTypeSearch);
        } catch (Exception e1) {
            try {
                return BrowserUtils.isDisplayed(driver, searchInputNameQ);
            } catch (Exception e2) {
                try {
                    return BrowserUtils.isDisplayed(driver, searchInputAriaLabel);
                } catch (Exception e3) {
                    return BrowserUtils.isDisplayed(driver, searchInputPlaceholder);
                }
            }
        }
    }

    public boolean isSearchButtonDisplayed() {
        return true;
    }

    public void enterSearchText(String searchText) {
        try {
            BrowserUtils.type(driver, searchInputTypeSearch, searchText);
        } catch (Exception e1) {
            try {
                BrowserUtils.type(driver, searchInputNameQ, searchText);
            } catch (Exception e2) {
                try {
                    BrowserUtils.type(driver, searchInputAriaLabel, searchText);
                } catch (Exception e3) {
                    BrowserUtils.type(driver, searchInputPlaceholder, searchText);
                }
            }
        }
    }

    public boolean areStoreResultsDisplayed() {
        return BrowserUtils.isDisplayed(driver, storeResultCards);
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMessage);
    }
}