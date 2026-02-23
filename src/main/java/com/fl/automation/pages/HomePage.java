package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class HomePage {
    private WebDriver driver;
    private WaitUtils waitUtils;
    private int timeout;

    private By findAStoreBtn = By.id("findStoreBtn");
    private By findStorePopup = By.id("findStorePopup");
    private By selectMyStoreLink = By.id("selectMyStoreLink");

    public HomePage(WebDriver driver, int timeout) {
        this.driver = driver;
        this.timeout = timeout;
        this.waitUtils = new WaitUtils(driver, timeout);
    }

    public boolean isFindAStorePopupDisplayed() {
        WebElement popup = waitUtils.waitForElementToBeVisible(driver.findElement(findStorePopup));
        return popup.isDisplayed();
    }

    public void clickFindAStore() {
        waitUtils.waitForElementToBeClickable(driver.findElement(findAStoreBtn)).click();
    }

    public boolean isSelectMyStoreLinkDisplayed() {
        WebElement link = waitUtils.waitForElementToBeVisible(driver.findElement(selectMyStoreLink));
        return link.isDisplayed();
    }

    public StoreLocatorPage clickSelectMyStore() {
        waitUtils.waitForElementToBeClickable(driver.findElement(selectMyStoreLink)).click();
        return new StoreLocatorPage(driver, timeout);
    }
}