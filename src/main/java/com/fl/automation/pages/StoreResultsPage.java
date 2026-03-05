package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import com.fl.automation.core.BrowserUtils;

public class StoreResultsPage {
    private WebDriver driver;
    private By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getStoreCards() {
        return driver.findElements(storeCards);
    }

    public boolean isStorePresent(String address) {
        for (WebElement card : getStoreCards()) {
            WebElement addr = null;
            try {
                addr = card.findElement(storeAddress);
            } catch (Exception e) {
                continue;
            }
            if (BrowserUtils.getText(driver, addr).contains(address)) {
                return true;
            }
        }
        return false;
    }

    public WebElement getStoreCardByAddress(String address) {
        for (WebElement card : getStoreCards()) {
            WebElement addr = null;
            try {
                addr = card.findElement(storeAddress);
            } catch (Exception e) {
                continue;
            }
            if (BrowserUtils.getText(driver, addr).contains(address)) {
                return card;
            }
        }
        return null;
    }

    public void clickSetMyStore(String address) {
        WebElement card = getStoreCardByAddress(address);
        if (card != null) {
            WebElement btn = card.findElement(setMyStoreButton);
            BrowserUtils.click(driver, btn);
        }
    }

    public boolean isEmptyResults() {
        try {
            return BrowserUtils.isDisplayed(driver, driver.findElement(emptyResultsMsg));
        } catch (Exception e) {
            return false;
        }
    }
}
