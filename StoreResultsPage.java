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
                List<WebElement> els = WaitUtils.waitForAllDisplayed(driver, locator);
                if (els != null && !els.isEmpty()) {
                    return els;
                }
            } catch (Exception ignored) {}
        }
        return new ArrayList<>();
    }
    public String getStoreAddress(WebElement card) {
        for (By locator : KBLocators.STORE_ADDRESS) {
            try {
                WebElement addr = card.findElement(locator);
                if (addr != null && addr.isDisplayed()) {
                    return addr.getText().trim();
                }
            } catch (Exception ignored) {}
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
        return null;
    }
    public boolean isEmptyResultsMessageDisplayed() {
        for (By locator : KBLocators.EMPTY_RESULTS_MESSAGE) {
            try {
                WebElement msg = WaitUtils.waitForDisplayed(driver, locator);
                if (msg != null && msg.isDisplayed()) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }
}
