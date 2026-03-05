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
            WebElement el = driver.findElement(selectMyStoreLink);
            if (BrowserUtils.isDisplayed(driver, el)) return true;
        } catch (Exception e) {}
        try {
            WebElement el = driver.findElement(selectMyStoreButton);
            if (BrowserUtils.isDisplayed(driver, el)) return true;
        } catch (Exception e) {}
        return false;
    }

    public void clickSelectMyStore() {
        WebElement el = null;
        try {
            el = driver.findElement(selectMyStoreLink);
            if (BrowserUtils.isDisplayed(driver, el)) {
                BrowserUtils.click(driver, el);
                return;
            }
        } catch (Exception e) {}
        el = driver.findElement(selectMyStoreButton);
        BrowserUtils.click(driver, el);
    }

    public WebElement getSearchInput() {
        try {
            WebElement el = driver.findElement(searchInput);
            if (BrowserUtils.isDisplayed(driver, el)) return el;
        } catch (Exception e) {}
        try {
            WebElement el = driver.findElement(searchInputFallback1);
            if (BrowserUtils.isDisplayed(driver, el)) return el;
        } catch (Exception e) {}
        try {
            WebElement el = driver.findElement(searchInputFallback2);
            if (BrowserUtils.isDisplayed(driver, el)) return el;
        } catch (Exception e) {}
        return driver.findElement(searchInputFallback3);
    }

    public boolean isSearchInputDisplayed() {
        try {
            WebElement el = getSearchInput();
            return BrowserUtils.isDisplayed(driver, el);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            WebElement el = driver.findElement(searchButton);
            return BrowserUtils.isDisplayed(driver, el);
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
