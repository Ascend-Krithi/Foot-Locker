package com.fl.automation.pages;

import com.fl.automation.utils.WaitUtils;
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
                List<WebElement> cards = WaitUtils.waitForElementsPresent(driver, locator);
                if (!cards.isEmpty()) return cards;
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Store result cards not found using KB locator fallbacks");
    }

    public WebElement getStoreCardByAddress(String address) {
        List<WebElement> cards = getStoreCards();
        for (WebElement card : cards) {
            for (By addrLocator : KBLocators.STORE_ADDRESS) {
                try {
                    WebElement addr = card.findElement(addrLocator);
                    if (addr.getText().trim().equalsIgnoreCase(address.trim())) {
                        return card;
                    }
                } catch (Exception ignored) {}
            }
        }
        return null;
    }

    public boolean isStoreWithAddressPresent(String address) {
        return getStoreCardByAddress(address) != null;
    }

    public boolean isEmptyResultsMessageDisplayed() {
        for (By locator : KBLocators.EMPTY_RESULTS_MESSAGE) {
            try {
                WebElement msg = WaitUtils.waitForElementDisplayed(driver, locator);
                if (msg.isDisplayed()) return true;
            } catch (Exception ignored) {}
        }
        return false;
    }

    public void clickSetMyStoreForAddress(String address) {
        WebElement card = getStoreCardByAddress(address);
        if (card == null) throw new RuntimeException("Store card with address not found: " + address);
        for (By locator : KBLocators.SET_MY_STORE_BUTTON) {
            try {
                WebElement btn = card.findElement(locator);
                if (btn.isDisplayed() && btn.isEnabled()) {
                    btn.click();
                    return;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Set My Store button not found in card for address: " + address);
    }
}
