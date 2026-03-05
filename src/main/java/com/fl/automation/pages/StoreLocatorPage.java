package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class StoreLocatorPage {
    private WebDriver driver;
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By searchInput = By.cssSelector("input[type='search']");
    private By searchInputFallback1 = By.cssSelector("input[name='q']");
    private By searchInputFallback2 = By.cssSelector("input[aria-label*='Search']");
    private By searchInputFallback3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By searchButton = By.xpath("//button[contains(.,'Search for Stores')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSelectMyStoreVisible() {
        try {
            if (BrowserUtils.isDisplayed(driver, driver.findElement(selectMyStoreLink))) return true;
            if (BrowserUtils.isDisplayed(driver, driver.findElement(selectMyStoreButton))) return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public void clickSelectMyStore() {
        WebElement el = null;
        if (BrowserUtils.isDisplayed(driver, driver.findElement(selectMyStoreLink))) {
            el = driver.findElement(selectMyStoreLink);
        } else {
            el = driver.findElement(selectMyStoreButton);
        }
        BrowserUtils.click(driver, el);
    }

    public WebElement getSearchInput() {
        if (BrowserUtils.isDisplayed(driver, driver.findElement(searchInput))) {
            return driver.findElement(searchInput);
        } else if (BrowserUtils.isDisplayed(driver, driver.findElement(searchInputFallback1))) {
            return driver.findElement(searchInputFallback1);
        } else if (BrowserUtils.isDisplayed(driver, driver.findElement(searchInputFallback2))) {
            return driver.findElement(searchInputFallback2);
        } else {
            return driver.findElement(searchInputFallback3);
        }
    }

    public boolean isSearchInputPresent() {
        try {
            if (BrowserUtils.isDisplayed(driver, driver.findElement(searchInput))) return true;
            if (BrowserUtils.isDisplayed(driver, driver.findElement(searchInputFallback1))) return true;
            if (BrowserUtils.isDisplayed(driver, driver.findElement(searchInputFallback2))) return true;
            if (BrowserUtils.isDisplayed(driver, driver.findElement(searchInputFallback3))) return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean isSearchButtonPresent() {
        try {
            return BrowserUtils.isDisplayed(driver, driver.findElement(searchButton));
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        BrowserUtils.type(driver, getSearchInput(), location);
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, driver.findElement(searchButton));
    }
}
