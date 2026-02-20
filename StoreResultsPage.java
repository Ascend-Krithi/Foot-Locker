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
                List<WebElement> els = WaitUtils.waitForAllPresence(driver, locator);
                if (els != null && !els.isEmpty()) return els;
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

    public String getAddressFromCard(WebElement card) {
        for (By addrLocator : KBLocators.STORE_ADDRESS) {
            try {
                WebElement addr = card.findElement(addrLocator);
                if (addr != null && !addr.getText().isEmpty()) {
                    return addr.getText().trim();
                }
            } catch (Exception ignored) {}
        }
        return null;
    }

    public WebElement getSetMyStoreButton(WebElement card) {
        for (By btnLocator : KBLocators.SET_MY_STORE_BUTTON) {
            try {
                WebElement btn = card.findElement(btnLocator);
                if (btn != null && btn.isDisplayed() && btn.isEnabled()) return btn;
            } catch (Exception ignored) {}
        }
        return null;
    }

    public boolean isEmptyResultsMessageDisplayed() {
        for (By locator : KBLocators.EMPTY_RESULTS_MESSAGE) {
            try {
                WebElement el = WaitUtils.waitForDisplayed(driver, locator);
                if (el != null && el.isDisplayed()) return true;
            } catch (Exception ignored) {}
        }
        return false;
    }
}
