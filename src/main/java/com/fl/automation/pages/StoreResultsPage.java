package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private int timeoutSec = 20;

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getStoreResultCards() {
        for (By locator : KBLocators.STORE_RESULT_CARDS) {
            try {
                List<WebElement> els = WaitUtils.waitForAllElementsPresent(driver, locator, timeoutSec);
                if (els != null && !els.isEmpty()) {
                    return els;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Store result cards not found using KB locator strategy");
    }

    public WebElement getStoreAddress(WebElement card) {
        for (By locator : KBLocators.STORE_ADDRESS) {
            try {
                WebElement el = card.findElement(locator);
                if (el != null && el.isDisplayed()) {
                    return el;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Store address not found in card using KB locator strategy");
    }

    public WebElement getSetMyStoreButton(WebElement card) {
        for (By locator : KBLocators.SET_MY_STORE_BUTTON) {
            try {
                WebElement el = card.findElement(locator);
                if (el != null && el.isDisplayed()) {
                    return el;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Set My Store button not found in card using KB locator strategy");
    }

    public WebElement getEmptyResultsMessage() {
        for (By locator : KBLocators.EMPTY_RESULTS_MESSAGE) {
            try {
                WebElement el = WaitUtils.waitForElementDisplayed(driver, locator, timeoutSec);
                if (el != null && el.isDisplayed()) {
                    return el;
                }
            } catch (Exception ignored) {}
        }
        return null;
    }
}
