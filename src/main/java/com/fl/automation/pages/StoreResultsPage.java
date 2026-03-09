package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;

    private By searchInputType = By.cssSelector("input[type='search']");
    private By searchInputName = By.cssSelector("input[name='q']");
    private By searchInputAria = By.cssSelector("input[aria-label*='Search']");
    private By searchInputPlaceholder = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    
    private By searchButton = By.xpath("//button[contains(.,'Search') or contains(.,'search')]");
    
    private By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        try {
            return BrowserUtils.isDisplayed(driver, searchInputType);
        } catch (Exception e1) {
            try {
                return BrowserUtils.isDisplayed(driver, searchInputName);
            } catch (Exception e2) {
                try {
                    return BrowserUtils.isDisplayed(driver, searchInputAria);
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
            BrowserUtils.type(driver, searchInputType, location);
        } catch (Exception e1) {
            try {
                BrowserUtils.type(driver, searchInputName, location);
            } catch (Exception e2) {
                try {
                    BrowserUtils.type(driver, searchInputAria, location);
                } catch (Exception e3) {
                    BrowserUtils.type(driver, searchInputPlaceholder, location);
                }
            }
        }
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, searchButton);
    }

    public List<WebElement> getStoreResults() {
        return WaitUtils.waitForPresenceOfAllElements(driver, storeCards);
    }

    public String getStoreAddress(WebElement storeCard) {
        WebElement addressElement = storeCard.findElement(storeAddress);
        return addressElement.getText();
    }

    public void clickSetMyStore(WebElement storeCard) {
        WebElement setButton = storeCard.findElement(setMyStoreButton);
        setButton.click();
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMessage);
    }

    public boolean isStoreAddressPresent(String expectedAddress) {
        List<WebElement> stores = getStoreResults();
        for (WebElement store : stores) {
            String address = getStoreAddress(store);
            if (address.contains(expectedAddress)) {
                return true;
            }
        }
        return false;
    }

    public WebElement findStoreByAddress(String expectedAddress) {
        List<WebElement> stores = getStoreResults();
        for (WebElement store : stores) {
            String address = getStoreAddress(store);
            if (address.contains(expectedAddress)) {
                return store;
            }
        }
        return null;
    }
}