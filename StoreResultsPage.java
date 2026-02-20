package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import com.fl.automation.core.BrowserUtils;
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
                List<WebElement> els = WaitUtils.waitForAllPresence(driver, locator);
                if (els != null && !els.isEmpty()) {
                    return els;
                }
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
                    if (addr != null && addr.getText().trim().equalsIgnoreCase(address.trim())) {
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

    public boolean isEmptyResultsMessagePresent() {
        for (By locator : KBLocators.EMPTY_RESULTS_MESSAGE) {
            try {
                WebElement el = WaitUtils.waitForDisplayed(driver, locator);
                if (el != null && el.isDisplayed()) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }

    public void clickSetMyStoreForAddress(String address) {
        WebElement card = getStoreCardByAddress(address);
        if (card == null) {
            throw new RuntimeException("Store card with address '" + address + "' not found");
        }
        for (By locator : KBLocators.SET_MY_STORE_BUTTON) {
            try {
                WebElement btn = card.findElement(locator);
                if (btn != null && btn.isDisplayed() && btn.isEnabled()) {
                    BrowserUtils.jsClick(driver, btn);
                    return;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Set My Store button not found in card for address: " + address);
    }
}
