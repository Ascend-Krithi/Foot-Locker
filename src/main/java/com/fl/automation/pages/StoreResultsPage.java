package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
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

    private By searchButton1 = By.cssSelector("button[type='submit']");
    private By searchButton2 = By.xpath("//button[contains(.,'Search')]");
    private By searchButton3 = By.xpath("//button[contains(.,'Find')]");
    private By searchButton4 = By.xpath("//button[contains(.,'Search for Stores')]");

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
        if (BrowserUtils.isDisplayed(driver, searchButton1)) {
            return true;
        } else if (BrowserUtils.isDisplayed(driver, searchButton2)) {
            return true;
        } else if (BrowserUtils.isDisplayed(driver, searchButton3)) {
            return true;
        } else {
            return BrowserUtils.isDisplayed(driver, searchButton4);
        }
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
        try {
            BrowserUtils.click(driver, searchButton1);
        } catch (Exception e1) {
            try {
                BrowserUtils.click(driver, searchButton2);
            } catch (Exception e2) {
                try {
                    BrowserUtils.click(driver, searchButton3);
                } catch (Exception e3) {
                    BrowserUtils.click(driver, searchButton4);
                }
            }
        }
    }

    public boolean areStoreResultsDisplayed() {
        return BrowserUtils.isDisplayed(driver, storeCards);
    }

    public List<WebElement> getStoreCards() {
        return WaitUtils.waitForElementsToBeVisible(driver, storeCards);
    }

    public boolean isStoreAddressDisplayed(String address) {
        List<WebElement> cards = getStoreCards();
        for (WebElement card : cards) {
            try {
                WebElement addressElement = card.findElement(storeAddress);
                if (addressElement.getText().contains(address)) {
                    return true;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }

    public void clickSetMyStoreForAddress(String address) {
        List<WebElement> cards = getStoreCards();
        for (WebElement card : cards) {
            try {
                WebElement addressElement = card.findElement(storeAddress);
                if (addressElement.getText().contains(address)) {
                    WebElement setButton = card.findElement(setMyStoreButton);
                    setButton.click();
                    return;
                }
            } catch (Exception e) {
                continue;
            }
        }
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMessage);
    }

    public boolean isSearchButtonEnabled() {
        try {
            return BrowserUtils.isEnabled(driver, searchButton1);
        } catch (Exception e1) {
            try {
                return BrowserUtils.isEnabled(driver, searchButton2);
            } catch (Exception e2) {
                try {
                    return BrowserUtils.isEnabled(driver, searchButton3);
                } catch (Exception e3) {
                    return BrowserUtils.isEnabled(driver, searchButton4);
                }
            }
        }
    }
}