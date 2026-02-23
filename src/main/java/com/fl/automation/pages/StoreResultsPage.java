package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import com.fl.automation.core.BrowserUtils;

public class StoreResultsPage {
    private WebDriver driver;
    private By searchInput = By.cssSelector("input[type='search']");
    private By searchInputAlt1 = By.cssSelector("input[name='q']");
    private By searchInputAlt2 = By.cssSelector("input[aria-label*='Search']");
    private By searchInputAlt3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By searchButton = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search')]");
    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddressInCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButtonInCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLocationTextboxVisible() {
        return BrowserUtils.isDisplayed(driver, searchInput) || BrowserUtils.isDisplayed(driver, searchInputAlt1) || BrowserUtils.isDisplayed(driver, searchInputAlt2) || BrowserUtils.isDisplayed(driver, searchInputAlt3);
    }

    public void enterLocation(String location) {
        if (BrowserUtils.isDisplayed(driver, searchInput)) {
            BrowserUtils.type(driver, searchInput, location);
        } else if (BrowserUtils.isDisplayed(driver, searchInputAlt1)) {
            BrowserUtils.type(driver, searchInputAlt1, location);
        } else if (BrowserUtils.isDisplayed(driver, searchInputAlt2)) {
            BrowserUtils.type(driver, searchInputAlt2, location);
        } else {
            BrowserUtils.type(driver, searchInputAlt3, location);
        }
    }

    public void clickSearchForStores() {
        BrowserUtils.click(driver, searchButton);
    }

    public boolean isSearchButtonVisible() {
        return BrowserUtils.isDisplayed(driver, searchButton);
    }

    public boolean areStoreResultsDisplayed() {
        return driver.findElements(storeResultCards).size() > 0;
    }

    public boolean isStoreWithAddressPresent(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddressInCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().contains(address)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setMyStoreByAddress(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddressInCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().contains(address)) {
                    WebElement setBtn = card.findElement(setMyStoreButtonInCard);
                    setBtn.click();
                    return;
                }
            }
        }
        throw new RuntimeException("Store with address not found: " + address);
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMessage);
    }

    public boolean isStoreSaved(String address) {
        // Assume some indicator appears on the card, e.g., a class or text
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddressInCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().contains(address)) {
                    String cardText = card.getText();
                    if (cardText.contains("My Store") || cardText.contains("Selected") || card.getAttribute("class").contains("selected")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isConfirmationIndicatorPresent(String address) {
        // Similar logic as isStoreSaved
        return isStoreSaved(address);
    }
}
