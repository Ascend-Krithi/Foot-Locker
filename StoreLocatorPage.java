package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreLocatorPage {
    private WebDriver driver;

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSelectMyStoreLink() {
        for (By locator : KBLocators.SELECT_MY_STORE) {
            try {
                WebElement el = WaitUtils.waitForDisplayed(driver, locator);
                if (el != null && el.isDisplayed()) {
                    return el;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Select My Store link/button not found using KB locator fallbacks");
    }

    public boolean isPopupDisplayed() {
        for (By locator : KBLocators.SELECT_MY_STORE) {
            try {
                WebElement el = WaitUtils.waitForDisplayed(driver, locator);
                if (el != null && el.isDisplayed()) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }

    public boolean isPopupMessageDisplayed(String expectedMsg) {
        try {
            By popupMsg = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");
            WebElement el = WaitUtils.waitForDisplayed(driver, popupMsg);
            return el != null && el.isDisplayed() && el.getText().contains(expectedMsg);
        } catch (Exception e) {
            return false;
        }
    }
}
