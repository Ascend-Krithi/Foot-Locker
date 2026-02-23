package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private By searchInput = By.cssSelector("input[type='search']");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        try {
            WebElement element = driver.findElement(searchInput);
            return BrowserUtils.isDisplayed(driver, element);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            WebElement element = driver.findElement(searchButton);
            return BrowserUtils.isDisplayed(driver, element);
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        WebElement element = driver.findElement(searchInput);
        BrowserUtils.type(driver, element, location);
    }

    public void clickSearchButton() {
        WebElement element = driver.findElement(searchButton);
        BrowserUtils.click(driver, element);
    }

    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> stores = driver.findElements(storeCards);
            return stores.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStoreAddressVisible(String address) {
        try {
            List<WebElement> stores = driver.findElements(storeCards);
            for (WebElement store : stores) {
                WebElement addressElement = store.findElement(storeAddress);
                String addressText = BrowserUtils.getText(driver, addressElement);
                if (addressText.contains(address)) {
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
            List<WebElement> stores = driver.findElements(storeCards);
            for (WebElement store : stores) {
                WebElement addressElement = store.findElement(storeAddress);
                String addressText = BrowserUtils.getText(driver, addressElement);
                if (addressText.contains(address)) {
                    WebElement setButton = store.findElement(setMyStoreButton);
                    BrowserUtils.click(driver, setButton);
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to set store: " + e.getMessage());
        }
    }

    public boolean isEmptyResultsMessageDisplayed() {
        try {
            WebElement element = driver.findElement(emptyResultsMessage);
            return BrowserUtils.isDisplayed(driver, element);
        } catch (Exception e) {
            return false;
        }
    }
}