package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import java.util.List;
import com.fl.automation.core.DriverFactory;

public class StoreResultsPage {
    private final WebDriver driver;

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public List<WebElement> getStoreCards() {
        for (By locator : KBLocators.STORE_RESULT_CARDS) {
            try {
                List<WebElement> els = WaitUtils.waitForAllPresent(locator);
                if (els != null && !els.isEmpty()) {
                    return els;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Store result cards not found using KB locator fallbacks");
    }

    public String getStoreAddress(WebElement card) {
        for (By locator : KBLocators.STORE_ADDRESS) {
            try {
                WebElement address = card.findElement(locator);
                if (address != null && address.isDisplayed()) {
                    return address.getText();
                }
            } catch (Exception ignored) {}
        }
        return null;
    }

    public boolean isEmptyResultsMessageDisplayed() {
        for (By locator : KBLocators.EMPTY_RESULTS_MESSAGE) {
            try {
                WebElement el = WaitUtils.waitForDisplayed(locator);
                if (el != null && el.isDisplayed()) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }
}
