package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;

    private By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By emptyResultsMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSelectMyStoreLinkDisplayed() {
        try {
            WebElement link = WaitUtils.waitForElementVisible(driver, selectMyStoreLink);
            return BrowserUtils.isDisplayed(link);
        } catch (Exception e1) {
            try {
                WebElement btn = WaitUtils.waitForElementVisible(driver, selectMyStoreButton);
                return BrowserUtils.isDisplayed(btn);
            } catch (Exception e2) {
                return false;
            }
        }
    }

    public void clickSelectMyStoreLink() {
        try {
            WebElement link = WaitUtils.waitForElementVisible(driver, selectMyStoreLink);
            BrowserUtils.click(link);
        } catch (Exception e1) {
            WebElement btn = WaitUtils.waitForElementVisible(driver, selectMyStoreButton);
            BrowserUtils.click(btn);
        }
    }

    public List<WebElement> getStoreCards() {
        return WaitUtils.waitForElementsVisible(driver, storeCards);
    }

    public String getStoreAddress(WebElement card) {
        WebElement address = card.findElement(storeAddress);
        return BrowserUtils.getText(address);
    }

    public void setMyStore(WebElement card) {
        WebElement btn = card.findElement(setMyStoreButton);
        BrowserUtils.click(btn);
    }

    public boolean isEmptyResultsMessageDisplayed() {
        try {
            WebElement msg = WaitUtils.waitForElementVisible(driver, emptyResultsMsg);
            return BrowserUtils.isDisplayed(msg);
        } catch (Exception e) {
            return false;
        }
    }
}
