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

    public List<WebElement> getStoreResultCards() {
        for (By locator : KBLocators.STORE_RESULT_CARDS) {
            try {
                List<WebElement> els = WaitUtils.waitForElementsPresent(driver, locator);
                if (els != null && !els.isEmpty()) {
                    return els;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Store result cards not found using KB locator strategy");
    }

    public WebElement getStoreCardByAddress(String address) {
        List<WebElement> cards = getStoreResultCards();
        for (WebElement card : cards) {
            for (By addrLocator : KBLocators.STORE_ADDRESS) {
                try {
                    WebElement addrEl = card.findElement(addrLocator);
                    if (addrEl != null && addrEl.getText().trim().equalsIgnoreCase(address.trim())) {
                        return card;
                    }
                } catch (Exception ignored) {}
            }
        }
        return null;
    }

    public WebElement getSetMyStoreButton(WebElement card) {
        for (By locator : KBLocators.SET_MY_STORE_BUTTON) {
            try {
                WebElement btn = card.findElement(locator);
                if (btn != null && btn.isDisplayed() && btn.isEnabled()) {
                    return btn;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Set My Store button not found in card using KB locator strategy");
    }

    public boolean isEmptyResultsMessageDisplayed() {
        for (By locator : KBLocators.EMPTY_RESULTS_MESSAGE) {
            try {
                WebElement msg = WaitUtils.waitForElementDisplayed(driver, locator);
                if (msg != null && msg.isDisplayed()) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }
}
