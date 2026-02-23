package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;

public class StoreResultsPage {
    private WebDriver driver;
    public static final By SEARCH_INPUT1 = By.cssSelector("input[type='search']");
    public static final By SEARCH_INPUT2 = By.cssSelector("input[name='q']");
    public static final By SEARCH_INPUT3 = By.cssSelector("input[aria-label*='Search']");
    public static final By SEARCH_INPUT4 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    public static final By SEARCH_BUTTON = By.cssSelector("button[type='submit'], button[aria-label*='Search']");
    public static final By STORE_CARDS = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    public static final By STORE_ADDRESS = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    public static final By SET_MY_STORE_BUTTON = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    public static final By EMPTY_RESULTS_MESSAGE = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        return BrowserUtils.isDisplayed(driver, SEARCH_INPUT1) || 
               BrowserUtils.isDisplayed(driver, SEARCH_INPUT2) || 
               BrowserUtils.isDisplayed(driver, SEARCH_INPUT3) || 
               BrowserUtils.isDisplayed(driver, SEARCH_INPUT4);
    }

    public boolean isSearchButtonDisplayed() {
        return BrowserUtils.isDisplayed(driver, SEARCH_BUTTON);
    }

    public void enterLocation(String location) {
        if (BrowserUtils.isDisplayed(driver, SEARCH_INPUT1)) {
            BrowserUtils.type(driver, SEARCH_INPUT1, location);
        } else if (BrowserUtils.isDisplayed(driver, SEARCH_INPUT2)) {
            BrowserUtils.type(driver, SEARCH_INPUT2, location);
        } else if (BrowserUtils.isDisplayed(driver, SEARCH_INPUT3)) {
            BrowserUtils.type(driver, SEARCH_INPUT3, location);
        } else if (BrowserUtils.isDisplayed(driver, SEARCH_INPUT4)) {
            BrowserUtils.type(driver, SEARCH_INPUT4, location);
        } else {
            throw new RuntimeException("Search input not found");
        }
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, SEARCH_BUTTON);
    }

    public void searchStore(String query) {
        enterLocation(query);
        clickSearchButton();
    }

    public List<WebElement> getStoreCards() {
        return driver.findElements(STORE_CARDS);
    }

    public String getStoreAddress(WebElement storeCard) {
        try {
            return storeCard.findElement(STORE_ADDRESS).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void setMyStore(WebElement storeCard) {
        WebElement btn = storeCard.findElement(SET_MY_STORE_BUTTON);
        btn.click();
    }

    public boolean isEmptyResults() {
        return BrowserUtils.isDisplayed(driver, EMPTY_RESULTS_MESSAGE);
    }

    public boolean isStoreResultsDisplayed() {
        return getStoreCards().size() > 0;
    }

    public WebElement findStoreByAddress(String address) {
        List<WebElement> stores = getStoreCards();
        for (WebElement store : stores) {
            String storeAddress = getStoreAddress(store);
            if (storeAddress.contains(address)) {
                return store;
            }
        }
        return null;
    }
}