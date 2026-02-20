package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getStoreCards() {
        for (By locator : KBLocators.STORE_RESULT_CARDS) {
            try {
                return WaitUtils.waitForAllPresence(driver, locator, 15);
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Store result cards not found");
    }

    public WebElement getStoreCardByAddress(String address) {
        List<WebElement> cards = getStoreCards();
        for (WebElement card : cards) {
            for (By locator : KBLocators.STORE_ADDRESS) {
                try {
                    WebElement addrElem = card.findElement(locator);
                    if (addrElem.getText().trim().equalsIgnoreCase(address.trim())) {
                        return card;
                    }
                } catch (Exception ignored) {}
            }
        }
        throw new RuntimeException("Store card with address not found: " + address);
    }

    public WebElement getSetMyStoreButton(WebElement card) {
        for (By locator : KBLocators.SET_MY_STORE_BUTTON) {
            try {
                return card.findElement(locator);
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Set My Store button not found in card");
    }

    public WebElement getEmptyResultsMessage() {
        for (By locator : KBLocators.EMPTY_RESULTS_MESSAGE) {
            try {
                return WaitUtils.waitForDisplayed(driver, locator, 15);
            } catch (Exception ignored) {}
        }
        return null;
    }
}