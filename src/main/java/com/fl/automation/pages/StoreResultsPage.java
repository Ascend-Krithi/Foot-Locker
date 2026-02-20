package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreResultsPage {
    private static final Logger logger = LogManager.getLogger(StoreResultsPage.class);
    private WebDriver driver;

    private static final By[] SEARCH_INPUT_LOCATORS = new By[] {
            By.cssSelector("input[type='search']"),
            By.cssSelector("input[name='q']"),
            By.cssSelector("input[aria-label*='Search']"),
            By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private static final By[] SEARCH_BUTTON_LOCATORS = new By[] {
            By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Find Stores') or contains(.,'Search')]")
    };

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLocationTextboxDisplayed() {
        logger.info("Checking if 'Location' textbox is displayed");
        for (By locator : SEARCH_INPUT_LOCATORS) {
            try {
                WebElement element = WaitUtils.waitForElementVisible(driver, locator);
                if (element != null && BrowserUtils.isDisplayed(element)) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }

    public boolean isSearchForStoresButtonDisplayed() {
        logger.info("Checking if 'Search for Stores' button is displayed");
        for (By locator : SEARCH_BUTTON_LOCATORS) {
            try {
                WebElement element = WaitUtils.waitForElementVisible(driver, locator);
                if (element != null && BrowserUtils.isDisplayed(element)) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }
}
