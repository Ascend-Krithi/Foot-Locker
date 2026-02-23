package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private WebDriver driver;
    private final By searchInput = By.cssSelector("input[type='search']");
    private final By searchInputFallback1 = By.cssSelector("input[name='q']");
    private final By searchInputFallback2 = By.cssSelector("input[aria-label*='Search']");
    private final By searchInputFallback3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearch(String searchText) {
        WebElement el;
        try {
            el = WaitUtils.waitForElementVisible(driver, searchInput);
        } catch (Exception e1) {
            try {
                el = WaitUtils.waitForElementVisible(driver, searchInputFallback1);
            } catch (Exception e2) {
                try {
                    el = WaitUtils.waitForElementVisible(driver, searchInputFallback2);
                } catch (Exception e3) {
                    el = WaitUtils.waitForElementVisible(driver, searchInputFallback3);
                }
            }
        }
        com.fl.automation.core.BrowserUtils.type(el, searchText);
        el.submit();
    }
}