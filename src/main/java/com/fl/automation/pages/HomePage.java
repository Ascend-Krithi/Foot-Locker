package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private static final Logger logger = LogManager.getLogger(HomePage.class);
    private WebDriver driver;

    private static final By[] FIND_STORE_HEADER_LOCATORS = new By[] {
            By.linkText("Find a Store"),
            By.cssSelector("header a[href*='stores.footlocker.com']"),
            By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFindAStore() {
        logger.info("Clicking 'Find a Store' link/button");
        WebElement element = null;
        for (By locator : FIND_STORE_HEADER_LOCATORS) {
            try {
                element = WaitUtils.waitForElementClickable(driver, locator);
                if (element != null && BrowserUtils.isDisplayed(element)) {
                    BrowserUtils.scrollIntoView(driver, element);
                    BrowserUtils.click(element);
                    return;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("'Find a Store' link/button not found using any locator");
    }
}
