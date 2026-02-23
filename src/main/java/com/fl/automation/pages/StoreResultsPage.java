package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;

    private By searchInput = By.cssSelector("input[type='search']");
    private By searchInputName = By.cssSelector("input[name='q']");
    private By searchInputAriaLabel = By.cssSelector("input[aria-label*='Search']");
    private By searchInputPlaceholder = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    
    private By searchButton = By.xpath("//button[contains(.,'Search') or contains(.,'search')]");
    
    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        try {
            return BrowserUtils.isDisplayed(driver, searchInput);
        } catch (Exception e1) {
            try {
                return BrowserUtils.isDisplayed(driver, searchInputName);
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
            BrowserUtils.type(driver, searchInput, location);
        } catch (Exception e1) {
            try {
                BrowserUtils.type(driver, searchInputName, location);
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
        List<WebElement> results = BrowserUtils.findElements(driver, storeResultCards);
        return results.size() > 0;
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMessage);
    }

    public boolean isStoreAddressPresent(String expectedAddress) {
        List<WebElement> storeCards = BrowserUtils.findElements(driver, storeResultCards);
        for (WebElement card : storeCards) {
            try {
                WebElement addressElement = card.findElement(storeAddress);
                String actualAddress = addressElement.getText().trim();
                if (actualAddress.contains(expectedAddress) || expectedAddress.contains(actualAddress)) {
                    return true;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }

    public void clickSetMyStoreForAddress(String address) {
        List<WebElement> storeCards = BrowserUtils.findElements(driver, storeResultCards);
        for (WebElement card : storeCards) {
            try {
                WebElement addressElement = card.findElement(storeAddress);
                String actualAddress = addressElement.getText().trim();
                if (actualAddress.contains(address) || address.contains(actualAddress)) {
                    WebElement setButton = card.findElement(setMyStoreButton);
                    setButton.click();
                    return;
                }
            } catch (Exception e) {
                continue;
            }
        }
    }

    public boolean isConfirmationDisplayed() {
        By confirmationIndicator = By.xpath("//*[contains(.,'selected') or contains(.,'confirmed') or contains(.,'My Store') or contains(.,'Your Store')]");
        return BrowserUtils.isDisplayed(driver, confirmationIndicator);
    }
}