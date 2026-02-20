package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.DriverFactory;

public class HomePage {
    private final WebDriver driver;

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void open(String url) {
        driver.get(url);
    }

    public WebElement getFindAStoreLink() {
        for (By locator : KBLocators.FIND_A_STORE) {
            try {
                WebElement el = WaitUtils.waitForDisplayed(locator);
                if (el != null && el.isDisplayed()) {
                    return el;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Find a Store link not found using KB locator fallbacks");
    }

    public void clickFindAStore() {
        WebElement el = getFindAStoreLink();
        BrowserUtils.jsClick(el);
    }

    public boolean isFindAStorePopupDisplayed() {
        for (By locator : KBLocators.SELECT_MY_STORE) {
            try {
                WebElement el = WaitUtils.waitForDisplayed(locator);
                if (el != null && el.isDisplayed()) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }

    public WebElement getSelectMyStoreLink() {
        for (By locator : KBLocators.SELECT_MY_STORE) {
            try {
                WebElement el = WaitUtils.waitForDisplayed(locator);
                if (el != null && el.isDisplayed()) {
                    return el;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Select My Store link not found using KB locator fallbacks");
    }

    public boolean isPopupMessageDisplayed(String message) {
        return driver.getPageSource().contains(message);
    }
}
