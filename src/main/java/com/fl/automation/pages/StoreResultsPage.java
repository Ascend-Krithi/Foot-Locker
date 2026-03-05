package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;

public class StoreResultsPage {
    private WebDriver driver;
    private By searchInputCss1 = By.cssSelector("input[type='search']");
    private By searchInputCss2 = By.cssSelector("input[name='q']");
    private By searchInputCss3 = By.cssSelector("input[aria-label*='Search']");
    private By searchInputCss4 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By searchButton = By.cssSelector("button[type='submit'], button[aria-label*='Search']");
    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddressInsideCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButtonInsideCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        return WaitUtils.waitForElementDisplayed(driver, getSearchInput());
    }

    public boolean isSearchButtonDisplayed() {
        try {
            WebElement btn = driver.findElement(searchButton);
            return WaitUtils.waitForElementDisplayed(driver, btn);
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement getSearchInput() {
        try {
            return driver.findElement(searchInputCss1);
        } catch (Exception e1) {
            try { return driver.findElement(searchInputCss2); } catch (Exception e2) {
                try { return driver.findElement(searchInputCss3); } catch (Exception e3) {
                    return driver.findElement(searchInputCss4);
                }
            }
        }
    }

    public void enterLocation(String location) {
        WebElement input = getSearchInput();
        BrowserUtils.type(input, location);
    }

    public void clickSearchButton() {
        WebElement btn = driver.findElement(searchButton);
        BrowserUtils.click(btn);
    }

    public boolean isStoreResultsDisplayed() {
        List<WebElement> cards = driver.findElements(storeResultCards);
        return cards != null && cards.size() > 0;
    }

    public WebElement getStoreCardByAddress(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            WebElement addrElem = null;
            try {
                addrElem = card.findElement(storeAddressInsideCard);
            } catch (Exception e) { continue; }
            if (addrElem != null && addrElem.getText().contains(address)) {
                return card;
            }
        }
        return null;
    }

    public boolean isStoreAddressDisplayed(String address) {
        WebElement card = getStoreCardByAddress(address);
        return card != null;
    }

    public void clickSetMyStoreOnCard(String address) {
        WebElement card = getStoreCardByAddress(address);
        if (card != null) {
            WebElement btn = card.findElement(setMyStoreButtonInsideCard);
            BrowserUtils.click(btn);
        } else {
            throw new RuntimeException("Store card with address not found: " + address);
        }
    }

    public boolean isConfirmationIndicatorDisplayed(String address) {
        WebElement card = getStoreCardByAddress(address);
        if (card != null) {
            String cardText = card.getText();
            return cardText.contains("My Store") || cardText.contains("Selected") || cardText.contains("Set as My Store");
        }
        return false;
    }

    public boolean isStoreInHeader(String address) {
        try {
            WebElement header = driver.findElement(By.cssSelector("header"));
            return header.getText().contains(address);
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToAnotherPage() {
        driver.navigate().to(driver.getCurrentUrl() + "?page=2");
    }

    public void returnToHomePage() {
        driver.navigate().to(com.fl.automation.core.ConfigReader.get("base.url"));
    }

    public boolean isStorePersistedInHeader(String address) {
        return isStoreInHeader(address);
    }
}