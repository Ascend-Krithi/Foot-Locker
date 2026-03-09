package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;

    private static final By STORE_CARDS = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private static final By STORE_ADDRESS = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private static final By SET_MY_STORE_BUTTON = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private static final By EMPTY_RESULTS_MESSAGE = By.xpath("//*[contains(.,'There are no locations in your search area')]");
    private static final By SEARCH_INPUT = By.cssSelector("input[type='search']");
    private static final By SEARCH_BUTTON = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        return BrowserUtils.isDisplayed(driver, SEARCH_INPUT);
    }

    public boolean isSearchButtonDisplayed() {
        return BrowserUtils.isDisplayed(driver, SEARCH_BUTTON);
    }

    public boolean areStoreResultsDisplayed() {
        return BrowserUtils.isDisplayed(driver, STORE_CARDS);
    }

    public boolean isStoreAddressDisplayed(String address) {
        try {
            List<WebElement> storeCards = driver.findElements(STORE_CARDS);
            for (WebElement card : storeCards) {
                try {
                    WebElement addressElement = card.findElement(STORE_ADDRESS);
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
        try {
            List<WebElement> storeCards = driver.findElements(STORE_CARDS);
            for (WebElement card : storeCards) {
                try {
                    WebElement addressElement = card.findElement(STORE_ADDRESS);
                    if (addressElement.getText().contains(address)) {
                        WebElement setMyStoreButton = card.findElement(SET_MY_STORE_BUTTON);
                        setMyStoreButton.click();
                        return;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not find and click Set My Store button for address: " + address);
        }
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, EMPTY_RESULTS_MESSAGE);
    }

    public boolean isConfirmationDisplayed() {
        By confirmationIndicator = By.xpath("//*[contains(.,'selected') or contains(.,'preferred') or contains(.,'My Store')]");
        return BrowserUtils.isDisplayed(driver, confirmationIndicator);
    }
}