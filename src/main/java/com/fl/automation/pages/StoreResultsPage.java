package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;

    private By searchInput1 = By.cssSelector("input[type='search']");
    private By searchInput2 = By.cssSelector("input[name='q']");
    private By searchInput3 = By.cssSelector("input[aria-label*='Search']");
    private By searchInput4 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");

    private By searchButton = By.xpath("//button[contains(.,'Search') or contains(@aria-label,'Search')]");

    private By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        try {
            return BrowserUtils.isDisplayed(driver, searchInput1);
        } catch (Exception e1) {
            try {
                return BrowserUtils.isDisplayed(driver, searchInput2);
            } catch (Exception e2) {
                try {
                    return BrowserUtils.isDisplayed(driver, searchInput3);
                } catch (Exception e3) {
                    return BrowserUtils.isDisplayed(driver, searchInput4);
                }
            }
        }
    }

    public boolean isSearchButtonDisplayed() {
        return BrowserUtils.isDisplayed(driver, searchButton);
    }

    public void enterSearchLocation(String location) {
        try {
            BrowserUtils.type(driver, searchInput1, location);
        } catch (Exception e1) {
            try {
                BrowserUtils.type(driver, searchInput2, location);
            } catch (Exception e2) {
                try {
                    BrowserUtils.type(driver, searchInput3, location);
                } catch (Exception e3) {
                    BrowserUtils.type(driver, searchInput4, location);
                }
            }
        }
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, searchButton);
    }

    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> stores = WaitUtils.waitForElementsToBeVisible(driver, storeCards);
            return stores.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMessage);
    }

    public boolean isStoreAddressPresent(String expectedAddress) {
        try {
            List<WebElement> stores = WaitUtils.waitForElementsToBeVisible(driver, storeCards);
            for (WebElement store : stores) {
                try {
                    WebElement addressElement = store.findElement(storeAddress);
                    String actualAddress = addressElement.getText().trim();
                    if (actualAddress.contains(expectedAddress) || expectedAddress.contains(actualAddress)) {
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

    public void clickSetMyStoreForAddress(String targetAddress) {
        try {
            List<WebElement> stores = WaitUtils.waitForElementsToBeVisible(driver, storeCards);
            for (WebElement store : stores) {
                try {
                    WebElement addressElement = store.findElement(storeAddress);
                    String actualAddress = addressElement.getText().trim();
                    if (actualAddress.contains(targetAddress) || targetAddress.contains(actualAddress)) {
                        WebElement setButton = store.findElement(setMyStoreButton);
                        setButton.click();
                        return;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not find and click Set My Store button for address: " + targetAddress);
        }
    }

    public boolean isConfirmationDisplayed() {
        By confirmationIndicator = By.xpath("//*[contains(.,'selected') or contains(.,'saved') or contains(.,'confirmed') or contains(.,'My Store')]");
        return BrowserUtils.isDisplayed(driver, confirmationIndicator);
    }

    public boolean isStoreVisibleInHeader(String storeName) {
        By storeInHeader = By.xpath("//header//*[contains(.," + storeName + ")]");
        return BrowserUtils.isDisplayed(driver, storeInHeader);
    }
}