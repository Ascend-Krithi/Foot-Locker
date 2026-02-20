package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getFindAStoreElement() {
        for (By locator : KBLocators.FIND_A_STORE) {
            try {
                WebElement el = WaitUtils.waitForElementDisplayed(driver, locator);
                if (el != null && el.isDisplayed()) {
                    return el;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Find a Store element not found using KB locator fallbacks");
    }

    public void clickFindAStore() {
        getFindAStoreElement().click();
    }

    public boolean isSelectMyStoreVisible() {
        for (By locator : KBLocators.SELECT_MY_STORE) {
            try {
                WebElement el = WaitUtils.waitForElementDisplayed(driver, locator);
                if (el != null && el.isDisplayed()) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }

    public void clickSelectMyStore() {
        for (By locator : KBLocators.SELECT_MY_STORE) {
            try {
                WebElement el = WaitUtils.waitForElementDisplayed(driver, locator);
                if (el != null && el.isDisplayed() && el.isEnabled()) {
                    el.click();
                    return;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Select My Store link/button not found using KB locator fallbacks");
    }
}
