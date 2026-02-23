package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreLocatorPage {

    private WebDriver driver;

    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By popupMessage = By.xpath("//*[contains(.,'Choose a preferred store to make shopping easier')]");

    public StoreLocatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectMyStore() {
        WebElement element = null;
        try {
            element = driver.findElement(selectMyStoreLink);
        } catch (Exception e) {
            element = driver.findElement(selectMyStoreButton);
        }
        BrowserUtils.click(driver, element);
    }

    public boolean isSelectMyStoreDisplayed() {
        WebElement element = null;
        try {
            element = driver.findElement(selectMyStoreLink);
        } catch (Exception e) {
            element = driver.findElement(selectMyStoreButton);
        }
        return BrowserUtils.isDisplayed(driver, element);
    }

    public boolean isPopupMessageDisplayed() {
        WebElement element = driver.findElement(popupMessage);
        return BrowserUtils.isDisplayed(driver, element);
    }

    public String getPopupMessageText() {
        WebElement element = driver.findElement(popupMessage);
        return BrowserUtils.getText(driver, element);
    }
}