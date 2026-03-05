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

    private By searchButton = By.xpath("//button[contains(.,'Search')]");

    private By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");

    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        try {
            return BrowserUtils.isDisplayed(driver, searchInput1);
        } catch (Exception e1) {
            try {
                return BrowserUtils.isDisplayed(driver, searchInput2);
            } catch (Exception e2) {
                try {
                    return BrowserUtils.isDisplayed(driver, searchInput3);
                } catch (Exception e3) {
                    return BrowserUtils.isDisplayed(driver, searchInput4);
                }
            }
        }
    }

    public boolean isSearchButtonDisplayed() {
        return BrowserUtils.isDisplayed(driver, searchButton);
    }

    public void enterLocation(String location) {
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
        List<WebElement> addresses = driver.findElements(storeAddress);
        for (WebElement address : addresses) {
            if (address.getText().contains(expectedAddress)) {
                return true;
            }
        }
        return false;
    }

    public void clickSetMyStoreForAddress(String targetAddress) {
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            String addressText = card.findElement(storeAddress).getText();
            if (addressText.contains(targetAddress)) {
                card.findElement(setMyStoreButton).click();
                break;
            }
        }
    }

    public boolean isConfirmationDisplayed() {
        try {
            return driver.getPageSource().contains("My Store") || driver.getPageSource().contains("selected");
        } catch (Exception e) {
            return false;
        }
    }
}