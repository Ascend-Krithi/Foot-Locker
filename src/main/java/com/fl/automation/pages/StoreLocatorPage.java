package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private static final Logger logger = LogManager.getLogger(StoreLocatorPage.class);
    private WebDriver driver;

    private static final By[] SELECT_MY_STORE_LOCATORS = new By[] {
            By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')"]),
            By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private static final By POPUP_MESSAGE = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPopupDisplayed() {
        logger.info("Checking if store locator popup is displayed");
        try {
            WebElement popup = WaitUtils.waitForElementVisible(driver, POPUP_MESSAGE);
            return BrowserUtils.isDisplayed(popup);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkDisplayed() {
        logger.info("Checking if 'Select My Store' link is displayed");
        for (By locator : SELECT_MY_STORE_LOCATORS) {
            try {
                WebElement element = WaitUtils.waitForElementVisible(driver, locator);
                if (element != null && BrowserUtils.isDisplayed(element)) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }

    public void clickSelectMyStore() {
        logger.info("Clicking 'Select My Store' link/button");
        for (By locator : SELECT_MY_STORE_LOCATORS) {
            try {
                WebElement element = WaitUtils.waitForElementClickable(driver, locator);
                if (element != null && BrowserUtils.isDisplayed(element)) {
                    BrowserUtils.scrollIntoView(driver, element);
                    BrowserUtils.click(element);
                    return;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("'Select My Store' link/button not found using any locator");
    }
}
