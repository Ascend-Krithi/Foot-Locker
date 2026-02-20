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

    public WebElement getSearchInput() {
        for (By locator : KBLocators.SEARCH_INPUT) {
            try {
                WebElement el = WaitUtils.waitForDisplayed(driver, locator);
                if (el != null && el.isDisplayed()) {
                    return el;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Search input not found using KB locator fallbacks");
    }

    public List<WebElement> getStoreResultCards() {
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

    public boolean isEmptyResultsMessageDisplayed() {
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
