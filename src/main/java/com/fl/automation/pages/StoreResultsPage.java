package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
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
    
    private By searchButton = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search')]");
    
    private By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
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
        return BrowserUtils.isDisplayed(driver, searchButton);
    }

    public void enterLocation(String location) {
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
        return BrowserUtils.isDisplayed(driver, storeCards);
    }

    public boolean isStoreAddressVisible(String address) {
        try {
            List<WebElement> stores = driver.findElements(storeCards);
            for (WebElement store : stores) {
                try {
                    WebElement addressElement = store.findElement(storeAddress);
                    if (addressElement.getText().contains(address)) {
                        return true;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSetMyStoreForAddress(String address) {
        List<WebElement> stores = driver.findElements(storeCards);
        for (WebElement store : stores) {
            try {
                WebElement addressElement = store.findElement(storeAddress);
                if (addressElement.getText().contains(address)) {
                    WebElement setButton = store.findElement(setMyStoreButton);
                    setButton.click();
                    return;
                }
            } catch (Exception e) {
                continue;
            }
        }
    }

    public boolean isConfirmationDisplayed() {
        By confirmationIndicator = By.xpath("//*[contains(.,'Your Store') or contains(.,'My Store') or contains(.,'Selected Store')]");
        return BrowserUtils.isDisplayed(driver, confirmationIndicator);
    }

    public boolean isNoStoresFoundMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMessage);
    }

    public boolean isSearchButtonEnabled() {
        try {
            WebElement button = WaitUtils.waitForElementToBeVisible(driver, searchButton);
            return button.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        By errorMessage = By.xpath("//*[contains(.,'required') or contains(.,'Required') or contains(.,'Please enter')]");
        return BrowserUtils.isDisplayed(driver, errorMessage);
    }
}