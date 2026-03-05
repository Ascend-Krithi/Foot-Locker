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
    private By searchInput = By.cssSelector("input[type='search']");
    private By searchInputFallback1 = By.cssSelector("input[name='q']");
    private By searchInputFallback2 = By.cssSelector("input[aria-label*='Search']");
    private By searchInputFallback3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By searchButton = By.xpath("//button[contains(.,'Search for Stores')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        try {
            WebElement el = driver.findElement(searchInput);
            if (BrowserUtils.isDisplayed(driver, el)) return true;
        } catch (Exception e) {}
        try {
            WebElement el = driver.findElement(searchInputFallback1);
            if (BrowserUtils.isDisplayed(driver, el)) return true;
        } catch (Exception e) {}
        try {
            WebElement el = driver.findElement(searchInputFallback2);
            if (BrowserUtils.isDisplayed(driver, el)) return true;
        } catch (Exception e) {}
        try {
            WebElement el = driver.findElement(searchInputFallback3);
            if (BrowserUtils.isDisplayed(driver, el)) return true;
        } catch (Exception e) {}
        return false;
    }

    public boolean isSearchButtonDisplayed() {
        try {
            WebElement el = driver.findElement(searchButton);
            return BrowserUtils.isDisplayed(driver, el);
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> getStoreCards() {
        return driver.findElements(storeCards);
    }

    public boolean isStorePresent(String address) {
        for (WebElement card : getStoreCards()) {
            try {
                WebElement addr = card.findElement(storeAddress);
                if (BrowserUtils.getText(driver, addr).contains(address)) {
                    return true;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }

    public WebElement getStoreCardByAddress(String address) {
        for (WebElement card : getStoreCards()) {
            try {
                WebElement addr = card.findElement(storeAddress);
                if (BrowserUtils.getText(driver, addr).contains(address)) {
                    return card;
                }
            } catch (Exception e) {
                continue;
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
            WebElement el = driver.findElement(emptyResultsMsg);
            return BrowserUtils.isDisplayed(driver, el);
        } catch (Exception e) {
            return false;
        }
    }
}
