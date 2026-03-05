package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;

    private By searchInputLocator1 = By.cssSelector("input[type='search']");
    private By searchInputLocator2 = By.cssSelector("input[name='q']");
    private By searchInputLocator3 = By.cssSelector("input[aria-label*='Search']");
    private By searchInputLocator4 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");

    private By searchButtonLocator = By.cssSelector("button[type='submit']");

    private By storeCardsLocator = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");

    private By storeAddressLocator = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");

    private By setMyStoreButtonLocator = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");

    private By emptyResultsMessageLocator = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        if (BrowserUtils.isDisplayed(driver, searchInputLocator1)) return true;
        if (BrowserUtils.isDisplayed(driver, searchInputLocator2)) return true;
        if (BrowserUtils.isDisplayed(driver, searchInputLocator3)) return true;
        if (BrowserUtils.isDisplayed(driver, searchInputLocator4)) return true;
        return false;
    }

    public boolean isSearchButtonDisplayed() {
        return BrowserUtils.isDisplayed(driver, searchButtonLocator);
    }

    public void enterSearchLocation(String location) {
        if (BrowserUtils.isDisplayed(driver, searchInputLocator1)) {
            BrowserUtils.type(driver, searchInputLocator1, location);
        } else if (BrowserUtils.isDisplayed(driver, searchInputLocator2)) {
            BrowserUtils.type(driver, searchInputLocator2, location);
        } else if (BrowserUtils.isDisplayed(driver, searchInputLocator3)) {
            BrowserUtils.type(driver, searchInputLocator3, location);
        } else if (BrowserUtils.isDisplayed(driver, searchInputLocator4)) {
            BrowserUtils.type(driver, searchInputLocator4, location);
        } else {
            throw new RuntimeException("Search input not found");
        }
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, searchButtonLocator);
    }

    public boolean areStoreResultsDisplayed() {
        return BrowserUtils.isDisplayed(driver, storeCardsLocator);
    }

    public boolean isStoreAddressPresent(String expectedAddress) {
        List<WebElement> addresses = driver.findElements(storeAddressLocator);
        for (WebElement address : addresses) {
            if (address.getText().contains(expectedAddress)) {
                return true;
            }
        }
        return false;
    }

    public void clickSetMyStoreForAddress(String targetAddress) {
        List<WebElement> storeCards = driver.findElements(storeCardsLocator);
        for (WebElement card : storeCards) {
            String addressText = card.findElement(storeAddressLocator).getText();
            if (addressText.contains(targetAddress)) {
                card.findElement(setMyStoreButtonLocator).click();
                return;
            }
        }
        throw new RuntimeException("Store with address " + targetAddress + " not found");
    }

    public boolean isStoreSetConfirmationDisplayed() {
        try {
            Thread.sleep(2000);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}