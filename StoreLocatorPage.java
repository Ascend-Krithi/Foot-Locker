package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class StoreLocatorPage {
    private WebDriver driver;
    private By selectMyStoreLink1 = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreLink2 = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By searchInput1 = By.cssSelector("input[type='search']");
    private By searchInput2 = By.cssSelector("input[name='q']");
    private By searchInput3 = By.cssSelector("input[aria-label*='Search']");
    private By searchInput4 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By searchButton = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMyStore() {
        WebElement el = null;
        try {
            el = driver.findElement(selectMyStoreLink1);
            if (BrowserUtils.isDisplayed(driver, el)) {
                BrowserUtils.click(driver, el);
                return;
            }
        } catch (Exception e) {}
        el = driver.findElement(selectMyStoreLink2);
        BrowserUtils.click(driver, el);
    }

    public boolean isLocationTextboxPresent() {
        try {
            return BrowserUtils.isDisplayed(driver, driver.findElement(searchInput1)) ||
                   BrowserUtils.isDisplayed(driver, driver.findElement(searchInput2)) ||
                   BrowserUtils.isDisplayed(driver, driver.findElement(searchInput3)) ||
                   BrowserUtils.isDisplayed(driver, driver.findElement(searchInput4));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonPresent() {
        try {
            return BrowserUtils.isDisplayed(driver, driver.findElement(searchButton));
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        WebElement el = null;
        try {
            el = driver.findElement(searchInput1);
            if (BrowserUtils.isDisplayed(driver, el)) {
                BrowserUtils.type(driver, el, location);
                return;
            }
        } catch (Exception e) {}
        try {
            el = driver.findElement(searchInput2);
            if (BrowserUtils.isDisplayed(driver, el)) {
                BrowserUtils.type(driver, el, location);
                return;
            }
        } catch (Exception e) {}
        try {
            el = driver.findElement(searchInput3);
            if (BrowserUtils.isDisplayed(driver, el)) {
                BrowserUtils.type(driver, el, location);
                return;
            }
        } catch (Exception e) {}
        el = driver.findElement(searchInput4);
        BrowserUtils.type(driver, el, location);
    }

    public void clickSearchButton() {
        WebElement el = driver.findElement(searchButton);
        BrowserUtils.click(driver, el);
    }
}
