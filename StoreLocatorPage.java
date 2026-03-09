package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private WebDriver driver;

    private static final By SEARCH_INPUT = By.cssSelector("input[type='search']");
    private static final By SEARCH_INPUT_FALLBACK1 = By.cssSelector("input[name='q']");
    private static final By SEARCH_INPUT_FALLBACK2 = By.cssSelector("input[aria-label*='Search']");
    private static final By SEARCH_INPUT_FALLBACK3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    
    private static final By SELECT_MY_STORE_LINK = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private static final By SELECT_MY_STORE_BUTTON = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    
    private static final By SEARCH_BUTTON = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMyStore() {
        try {
            BrowserUtils.click(driver, SELECT_MY_STORE_LINK);
        } catch (Exception e) {
            BrowserUtils.click(driver, SELECT_MY_STORE_BUTTON);
        }
    }

    public void enterLocation(String location) {
        try {
            BrowserUtils.sendKeys(driver, SEARCH_INPUT, location);
        } catch (Exception e1) {
            try {
                BrowserUtils.sendKeys(driver, SEARCH_INPUT_FALLBACK1, location);
            } catch (Exception e2) {
                try {
                    BrowserUtils.sendKeys(driver, SEARCH_INPUT_FALLBACK2, location);
                } catch (Exception e3) {
                    BrowserUtils.sendKeys(driver, SEARCH_INPUT_FALLBACK3, location);
                }
            }
        }
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, SEARCH_BUTTON);
    }

    public boolean isSearchInputDisplayed() {
        try {
            return BrowserUtils.isDisplayed(driver, SEARCH_INPUT);
        } catch (Exception e1) {
            try {
                return BrowserUtils.isDisplayed(driver, SEARCH_INPUT_FALLBACK1);
            } catch (Exception e2) {
                try {
                    return BrowserUtils.isDisplayed(driver, SEARCH_INPUT_FALLBACK2);
                } catch (Exception e3) {
                    return BrowserUtils.isDisplayed(driver, SEARCH_INPUT_FALLBACK3);
                }
            }
        }
    }

    public boolean isSearchButtonDisplayed() {
        return BrowserUtils.isDisplayed(driver, SEARCH_BUTTON);
    }

    public boolean isSelectMyStoreDisplayed() {
        try {
            return BrowserUtils.isDisplayed(driver, SELECT_MY_STORE_LINK);
        } catch (Exception e) {
            return BrowserUtils.isDisplayed(driver, SELECT_MY_STORE_BUTTON);
        }
    }
}