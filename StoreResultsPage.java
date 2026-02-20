package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
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
        return new ArrayList<>();
    }

    public boolean isStoreWithAddressPresent(String address) {
        List<WebElement> cards = getStoreCards();
        for (WebElement card : cards) {
            for (By addrLocator : KBLocators.STORE_ADDRESS) {
                try {
                    WebElement addrEl = card.findElement(addrLocator);
                    if (addrEl != null && addrEl.getText().trim().equalsIgnoreCase(address.trim())) {
                        return true;
                    }
                } catch (Exception ignored) {}
            }
        }
        return false;
    }

    public boolean clickSetMyStoreForAddress(String address) {
        List<WebElement> cards = getStoreCards();
        for (WebElement card : cards) {
            for (By addrLocator : KBLocators.STORE_ADDRESS) {
                try {
                    WebElement addrEl = card.findElement(addrLocator);
                    if (addrEl != null && addrEl.getText().trim().equalsIgnoreCase(address.trim())) {
                        for (By btnLocator : KBLocators.SET_MY_STORE_BUTTON) {
                            try {
                                WebElement btn = card.findElement(btnLocator);
                                if (btn != null && btn.isDisplayed() && btn.isEnabled()) {
                                    btn.click();
                                    return true;
                                }
                            } catch (Exception ignored) {}
                        }
                    }
                } catch (Exception ignored) {}
            }
        }
        return false;
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
}
