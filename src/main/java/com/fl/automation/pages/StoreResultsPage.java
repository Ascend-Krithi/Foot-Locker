package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private WaitUtils waitUtils;
    private int timeout;

    private By storeList = By.cssSelector(".store-list .store-item");
    private By storeAddress = By.cssSelector(".store-address");
    private By setMyStoreBtn = By.cssSelector(".set-my-store-btn");
    private By confirmationIndicator = By.id("confirmationIndicator");
    private By preferredStore = By.cssSelector(".preferred-store");

    public StoreResultsPage(WebDriver driver, int timeout) {
        this.driver = driver;
        this.timeout = timeout;
        this.waitUtils = new WaitUtils(driver, timeout);
    }

    public int getStoreCount() {
        List<WebElement> stores = driver.findElements(storeList);
        return stores.size();
    }

    public String getStoreAddress(int index) {
        List<WebElement> stores = driver.findElements(storeList);
        if (index < stores.size()) {
            WebElement address = stores.get(index).findElement(storeAddress);
            return address.getText();
        }
        return null;
    }

    public void setMyStore(int index) {
        List<WebElement> stores = driver.findElements(storeList);
        if (index < stores.size()) {
            WebElement btn = stores.get(index).findElement(setMyStoreBtn);
            waitUtils.waitForElementToBeClickable(btn).click();
        }
    }

    public boolean isConfirmationIndicatorDisplayed() {
        WebElement indicator = waitUtils.waitForElementToBeVisible(driver.findElement(confirmationIndicator));
        return indicator.isDisplayed();
    }

    public boolean isPreferredStoreDisplayed() {
        List<WebElement> preferred = driver.findElements(preferredStore);
        return !preferred.isEmpty() && preferred.get(0).isDisplayed();
    }
}