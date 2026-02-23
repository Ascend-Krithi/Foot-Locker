package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;

    private By searchInputTypeSearch = By.cssSelector("input[type='search']");
    private By searchInputNameQ = By.cssSelector("input[name='q']");
    private By searchInputAriaLabel = By.cssSelector("input[aria-label*='Search']");
    private By searchInputPlaceholder = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By searchButton = By.xpath("//button[contains(.,'Search') or contains(.,'Search for Stores')]");
    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");
    private By confirmationIndicator = By.xpath("//*[contains(.,'is now your preferred store') or contains(.,'Store selected') or contains(.,'Your store has been set')]");

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
        return BrowserUtils.isDisplayed(driver, searchButton);
    }

    public void enterSearchLocation(String location) {
        try {
            BrowserUtils.type(driver, searchInputTypeSearch, location);
        } catch (Exception e1) {
            try {
                BrowserUtils.type(driver, searchInputNameQ, location);
            } catch (Exception e2) {
                try {
                    BrowserUtils.type(driver, searchInputAriaLabel, location);
                } catch (Exception e3) {
                    BrowserUtils.type(driver, searchInputPlaceholder, location);
                }
            }
        }
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, searchButton);
    }

    public boolean areStoreResultsDisplayed() {
        return BrowserUtils.isDisplayed(driver, storeResultCards);
    }

    public boolean isSpecificAddressPresent(String address) {
        try {
            List<WebElement> addresses = driver.findElements(storeAddress);
            for (WebElement addr : addresses) {
                if (addr.getText().contains(address)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSetMyStoreForAddress(String address) {
        try {
            List<WebElement> storeCards = driver.findElements(storeResultCards);
            for (WebElement card : storeCards) {
                String cardText = card.getText();
                if (cardText.contains(address)) {
                    WebElement setButton = card.findElement(setMyStoreButton);
                    setButton.click();
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to set store for address: " + address, e);
        }
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMessage);
    }

    public boolean isConfirmationIndicatorDisplayed() {
        return BrowserUtils.isDisplayed(driver, confirmationIndicator);
    }

    public boolean isPreferredStoreVisible() {
        try {
            By preferredStoreIndicator = By.xpath("//*[contains(.,'Your Store') or contains(.,'Preferred Store') or contains(.,'My Store')]");
            return BrowserUtils.isDisplayed(driver, preferredStoreIndicator);
        } catch (Exception e) {
            return false;
        }
    }
}