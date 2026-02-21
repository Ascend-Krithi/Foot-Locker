package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreLocatorPage {
    private WebDriver driver;
    private final By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By searchInput = By.cssSelector("input[type='search']");
    private final By searchInputName = By.cssSelector("input[name='q']");
    private final By searchInputAria = By.cssSelector("input[aria-label*='Search']");
    private final By searchInputPlaceholder = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private final By searchButton = By.cssSelector("button[type='submit'], button[aria-label*='Search']");
    private final By popupMessage = By.xpath("//*[contains(text(),'Choose a preferred store to make shopping easier')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPopupDisplayed() {
        return BrowserUtils.isDisplayed(driver, popupMessage);
    }

    public boolean isSelectMyStoreLinkDisplayed() {
        return BrowserUtils.isDisplayed(driver, selectMyStoreLink) || BrowserUtils.isDisplayed(driver, selectMyStoreButton);
    }

    public void clickSelectMyStore() {
        if (BrowserUtils.isDisplayed(driver, selectMyStoreLink)) {
            BrowserUtils.click(driver, selectMyStoreLink);
        } else {
            BrowserUtils.click(driver, selectMyStoreButton);
        }
    }

    public void enterSearchText(String text) {
        if (BrowserUtils.isDisplayed(driver, searchInput)) {
            BrowserUtils.type(driver, searchInput, text);
        } else if (BrowserUtils.isDisplayed(driver, searchInputName)) {
            BrowserUtils.type(driver, searchInputName, text);
        } else if (BrowserUtils.isDisplayed(driver, searchInputAria)) {
            BrowserUtils.type(driver, searchInputAria, text);
        } else {
            BrowserUtils.type(driver, searchInputPlaceholder, text);
        }
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, searchButton);
    }
}
