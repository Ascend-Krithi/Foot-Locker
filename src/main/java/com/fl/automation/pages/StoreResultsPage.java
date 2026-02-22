package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;

    private By searchInput1 = By.cssSelector("input[type='search']");
    private By searchInput2 = By.cssSelector("input[name='q']");
    private By searchInput3 = By.cssSelector("input[aria-label*='Search']");
    private By searchInput4 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");

    private By searchButton = By.xpath("//button[contains(.,'Search') or contains(.,'Search for Stores')]");

    private By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        if (BrowserUtils.isDisplayed(driver, searchInput1)) {
            return true;
        } else if (BrowserUtils.isDisplayed(driver, searchInput2)) {
            return true;
        } else if (BrowserUtils.isDisplayed(driver, searchInput3)) {
            return true;
        } else {
            return BrowserUtils.isDisplayed(driver, searchInput4);
        }
    }

    public boolean isSearchButtonDisplayed() {
        return BrowserUtils.isDisplayed(driver, searchButton);
    }

    public void enterSearchLocation(String location) {
        try {
            BrowserUtils.type(driver, searchInput1, location);
        } catch (Exception e1) {
            try {
                BrowserUtils.type(driver, searchInput2, location);
            } catch (Exception e2) {
                try {
                    BrowserUtils.type(driver, searchInput3, location);
                } catch (Exception e3) {
                    BrowserUtils.type(driver, searchInput4, location);
                }
            }
        }
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, searchButton);
    }

    public boolean areStoreResultsDisplayed() {
        return BrowserUtils.isDisplayed(driver, storeCards);
    }

    public boolean isStoreAddressPresent(String expectedAddress) {
        List<WebElement> addresses = BrowserUtils.getElements(driver, storeAddress);
        for (WebElement address : addresses) {
            if (address.getText().contains(expectedAddress)) {
                return true;
            }
        }
        return false;
    }

    public void clickSetMyStoreForAddress(String targetAddress) {
        List<WebElement> storeCardsList = BrowserUtils.getElements(driver, storeCards);
        for (WebElement card : storeCardsList) {
            String cardText = card.getText();
            if (cardText.contains(targetAddress)) {
                WebElement setButton = card.findElement(setMyStoreButton);
                setButton.click();
                break;
            }
        }
    }

    public boolean isStoreSetConfirmationDisplayed() {
        try {
            By confirmationIndicator = By.xpath("//*[contains(.,'My Store') or contains(.,'Your Store') or contains(.,'Selected')]");
            return BrowserUtils.isDisplayed(driver, confirmationIndicator);
        } catch (Exception e) {
            return false;
        }
    }
}