package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import com.fl.automation.core.BrowserUtils;

public class StoreResultsPage {
    private WebDriver driver;

    private final By searchInput1 = By.cssSelector("input[type='search']");
    private final By searchInput2 = By.cssSelector("input[name='q']");
    private final By searchInput3 = By.cssSelector("input[aria-label*='Search']");
    private final By searchInput4 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private final By searchButton = By.cssSelector("button[type='submit'], button[aria-label*='Search']");
    private final By storeCard = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private final By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private final By emptyResultsMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSearchInput() {
        if (driver.findElements(searchInput1).size() > 0) {
            return driver.findElement(searchInput1);
        } else if (driver.findElements(searchInput2).size() > 0) {
            return driver.findElement(searchInput2);
        } else if (driver.findElements(searchInput3).size() > 0) {
            return driver.findElement(searchInput3);
        } else if (driver.findElements(searchInput4).size() > 0) {
            return driver.findElement(searchInput4);
        }
        throw new RuntimeException("Search input not found");
    }

    public void search(String query) {
        WebElement input = getSearchInput();
        BrowserUtils.type(driver, input, query);
        List<WebElement> buttons = driver.findElements(searchButton);
        if (!buttons.isEmpty()) {
            BrowserUtils.click(driver, buttons.get(0));
        }
    }

    public List<WebElement> getStoreCards() {
        return driver.findElements(storeCard);
    }

    public String getStoreAddress(WebElement card) {
        List<WebElement> addresses = card.findElements(storeAddress);
        if (!addresses.isEmpty()) {
            return addresses.get(0).getText();
        }
        return null;
    }

    public void setMyStore(WebElement card) {
        List<WebElement> buttons = card.findElements(setMyStoreButton);
        if (!buttons.isEmpty()) {
            BrowserUtils.click(driver, buttons.get(0));
        } else {
            throw new RuntimeException("Set My Store button not found in card");
        }
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return !driver.findElements(emptyResultsMsg).isEmpty();
    }
}
