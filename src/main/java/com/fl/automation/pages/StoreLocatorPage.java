package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {
    private WebDriver driver;

    private By searchInput = By.cssSelector("input[type='search']");
    private By searchInputFallback1 = By.cssSelector("input[name='q']");
    private By searchInputFallback2 = By.cssSelector("input[aria-label*='Search']");
    private By searchInputFallback3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By searchButton = By.cssSelector("button[type='submit'], button[aria-label*='Search']");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterLocation(String location) {
        WebElement input;
        try {
            input = WaitUtils.waitForElementVisible(driver, searchInput);
        } catch (Exception e1) {
            try {
                input = WaitUtils.waitForElementVisible(driver, searchInputFallback1);
            } catch (Exception e2) {
                try {
                    input = WaitUtils.waitForElementVisible(driver, searchInputFallback2);
                } catch (Exception e3) {
                    input = WaitUtils.waitForElementVisible(driver, searchInputFallback3);
                }
            }
        }
        BrowserUtils.type(input, location);
    }

    public void clickSearch() {
        WebElement button = WaitUtils.waitForElementClickable(driver, searchButton);
        BrowserUtils.click(button);
    }
}
