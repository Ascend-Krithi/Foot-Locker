package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;

public class StoreLocatorPage {
    private WebDriver driver;

    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By searchInput = By.cssSelector("input[type=search]");
    private By searchInputName = By.cssSelector("input[name=q]");
    private By searchInputAria = By.cssSelector("input[aria-label*=Search]");
    private By searchInputPlaceholder = By.cssSelector("input[placeholder*=Search i], input[placeholder*=City i], input[placeholder*=ZIP i]");
    private By searchButton = By.xpath("//button[contains(.,'Search for Stores')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMyStore() {
        WebElement element = null;
        if (BrowserUtils.isDisplayed(driver, driver.findElement(selectMyStoreLink))) {
            element = driver.findElement(selectMyStoreLink);
        } else {
            element = driver.findElement(selectMyStoreButton);
        }
        BrowserUtils.click(driver, element);
    }

    public void enterLocation(String location) {
        WebElement input = null;
        if (BrowserUtils.isDisplayed(driver, driver.findElement(searchInput))) {
            input = driver.findElement(searchInput);
        } else if (BrowserUtils.isDisplayed(driver, driver.findElement(searchInputName))) {
            input = driver.findElement(searchInputName);
        } else if (BrowserUtils.isDisplayed(driver, driver.findElement(searchInputAria))) {
            input = driver.findElement(searchInputAria);
        } else {
            input = driver.findElement(searchInputPlaceholder);
        }
        BrowserUtils.type(driver, input, location);
    }

    public void clickSearchForStores() {
        WebElement button = driver.findElement(searchButton);
        BrowserUtils.click(driver, button);
    }

    public boolean isLocationTextboxDisplayed() {
        try {
            return BrowserUtils.isDisplayed(driver, driver.findElement(searchInput)) ||
                   BrowserUtils.isDisplayed(driver, driver.findElement(searchInputName)) ||
                   BrowserUtils.isDisplayed(driver, driver.findElement(searchInputAria)) ||
                   BrowserUtils.isDisplayed(driver, driver.findElement(searchInputPlaceholder));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            return BrowserUtils.isDisplayed(driver, driver.findElement(searchButton));
        } catch (Exception e) {
            return false;
        }
    }
}
