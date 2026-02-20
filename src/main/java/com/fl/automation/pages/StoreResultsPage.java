package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private By searchInput = By.cssSelector("input[type=search]");
    private By searchInputName = By.cssSelector("input[name=q]");
    private By searchInputAria = By.cssSelector("input[aria-label*=Search]");
    private By searchInputPlaceholder = By.cssSelector("input[placeholder*=Search i], input[placeholder*=City i], input[placeholder*=ZIP i]");
    private By searchButton = By.cssSelector("button[type=submit], button[data-qa=search], button[aria-label*=Search]");
    private By storeResultCards = By.cssSelector("[data-qa=location], .c-location-card, .location, [class*=location-card]");
    private By storeAddress = By.cssSelector("[data-qa=address], .c-address, address, .address, [class*=address]");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearch(String searchText) {
        if (BrowserUtils.isDisplayed(driver, searchInput)) {
            BrowserUtils.type(driver, searchInput, searchText);
        } else if (BrowserUtils.isDisplayed(driver, searchInputName)) {
            BrowserUtils.type(driver, searchInputName, searchText);
        } else if (BrowserUtils.isDisplayed(driver, searchInputAria)) {
            BrowserUtils.type(driver, searchInputAria, searchText);
        } else {
            BrowserUtils.type(driver, searchInputPlaceholder, searchText);
        }
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, searchButton);
    }

    public int getStoreResultsCount() {
        List<WebElement> cards = driver.findElements(storeResultCards);
        return cards.size();
    }

    public String getStoreAddress(int index) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        if (cards.size() > index) {
            WebElement address = cards.get(index).findElement(storeAddress);
            return address.getText();
        }
        return null;
    }

    public boolean isSetMyStoreButtonPresent(int index) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        if (cards.size() > index) {
            return cards.get(index).findElements(setMyStoreButton).size() > 0;
        }
        return false;
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMsg);
    }
}
