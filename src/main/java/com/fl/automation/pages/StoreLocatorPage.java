package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private WebDriver driver;
    private By popupMessage = By.xpath("//*[contains(.,'Choose a preferred store')]");
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By searchInput1 = By.cssSelector("input[type='search']");
    private By searchInput2 = By.cssSelector("input[name='q']");
    private By searchInput3 = By.cssSelector("input[aria-label*='Search']");
    private By searchInput4 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By searchButton1 = By.cssSelector("button[type='submit']");
    private By searchButton2 = By.xpath("//button[contains(.,'Search')]");
    private By searchButton3 = By.xpath("//button[contains(.,'Find')]");
    private By searchButton4 = By.xpath("//button[contains(.,'Search for Stores')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPopupMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, popupMessage);
    }

    public void clickSelectMyStore() {
        if (BrowserUtils.isDisplayed(driver, selectMyStoreLink)) {
            BrowserUtils.click(driver, selectMyStoreLink);
        } else {
            BrowserUtils.click(driver, selectMyStoreButton);
        }
    }

    public boolean isLocationTextboxDisplayed() {
        return BrowserUtils.isDisplayed(driver, searchInput1) ||
               BrowserUtils.isDisplayed(driver, searchInput2) ||
               BrowserUtils.isDisplayed(driver, searchInput3) ||
               BrowserUtils.isDisplayed(driver, searchInput4);
    }

    public boolean isSearchForStoresButtonDisplayed() {
        return BrowserUtils.isDisplayed(driver, searchButton1) ||
               BrowserUtils.isDisplayed(driver, searchButton2) ||
               BrowserUtils.isDisplayed(driver, searchButton3) ||
               BrowserUtils.isDisplayed(driver, searchButton4);
    }

    public void enterLocation(String location) {
        if (BrowserUtils.isDisplayed(driver, searchInput1)) {
            BrowserUtils.type(driver, searchInput1, location);
        } else if (BrowserUtils.isDisplayed(driver, searchInput2)) {
            BrowserUtils.type(driver, searchInput2, location);
        } else if (BrowserUtils.isDisplayed(driver, searchInput3)) {
            BrowserUtils.type(driver, searchInput3, location);
        } else {
            BrowserUtils.type(driver, searchInput4, location);
        }
    }

    public void clickSearchForStores() {
        if (BrowserUtils.isDisplayed(driver, searchButton4)) {
            BrowserUtils.click(driver, searchButton4);
        } else if (BrowserUtils.isDisplayed(driver, searchButton2)) {
            BrowserUtils.click(driver, searchButton2);
        } else if (BrowserUtils.isDisplayed(driver, searchButton3)) {
            BrowserUtils.click(driver, searchButton3);
        } else {
            BrowserUtils.click(driver, searchButton1);
        }
    }

    public boolean isSearchButtonEnabled() {
        if (BrowserUtils.isDisplayed(driver, searchButton1)) {
            return driver.findElement(searchButton1).isEnabled();
        } else if (BrowserUtils.isDisplayed(driver, searchButton2)) {
            return driver.findElement(searchButton2).isEnabled();
        } else if (BrowserUtils.isDisplayed(driver, searchButton3)) {
            return driver.findElement(searchButton3).isEnabled();
        } else if (BrowserUtils.isDisplayed(driver, searchButton4)) {
            return driver.findElement(searchButton4).isEnabled();
        }
        return false;
    }
}
