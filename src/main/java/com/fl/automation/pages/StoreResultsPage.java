package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {

    private WebDriver driver;

    private By searchInputTypeSearch = By.cssSelector("input[type='search']");
    private By searchInputNameQ = By.cssSelector("input[name='q']");
    private By searchInputAriaLabel = By.cssSelector("input[aria-label*='Search']");
    private By searchInputPlaceholder = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By searchButton = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search')]");
    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");
    private By confirmationIndicator = By.xpath("//*[contains(.,'Your store has been set') or contains(.,'Store selected') or contains(.,'Preferred store')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        WebElement element = null;
        try {
            element = driver.findElement(searchInputTypeSearch);
        } catch (Exception e1) {
            try {
                element = driver.findElement(searchInputNameQ);
            } catch (Exception e2) {
                try {
                    element = driver.findElement(searchInputAriaLabel);
                } catch (Exception e3) {
                    element = driver.findElement(searchInputPlaceholder);
                }
            }
        }
        return BrowserUtils.isDisplayed(driver, element);
    }

    public boolean isSearchButtonDisplayed() {
        WebElement element = driver.findElement(searchButton);
        return BrowserUtils.isDisplayed(driver, element);
    }

    public void enterSearchLocation(String location) {
        WebElement element = null;
        try {
            element = driver.findElement(searchInputTypeSearch);
        } catch (Exception e1) {
            try {
                element = driver.findElement(searchInputNameQ);
            } catch (Exception e2) {
                try {
                    element = driver.findElement(searchInputAriaLabel);
                } catch (Exception e3) {
                    element = driver.findElement(searchInputPlaceholder);
                }
            }
        }
        BrowserUtils.type(driver, element, location);
    }

    public void clickSearchButton() {
        WebElement element = driver.findElement(searchButton);
        BrowserUtils.click(driver, element);
    }

    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> results = driver.findElements(storeResultCards);
            return results.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStoreAddressPresent(String address) {
        try {
            List<WebElement> storeCards = driver.findElements(storeResultCards);
            for (WebElement card : storeCards) {
                try {
                    WebElement addressElement = card.findElement(storeAddress);
                    String addressText = BrowserUtils.getText(driver, addressElement);
                    if (addressText.contains(address)) {
                        return true;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSetMyStoreForAddress(String address) {
        List<WebElement> storeCards = driver.findElements(storeResultCards);
        for (WebElement card : storeCards) {
            try {
                WebElement addressElement = card.findElement(storeAddress);
                String addressText = BrowserUtils.getText(driver, addressElement);
                if (addressText.contains(address)) {
                    WebElement setStoreButton = card.findElement(setMyStoreButton);
                    BrowserUtils.click(driver, setStoreButton);
                    return;
                }
            } catch (Exception e) {
                continue;
            }
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            WebElement element = driver.findElement(emptyResultsMessage);
            return BrowserUtils.isDisplayed(driver, element);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isConfirmationIndicatorDisplayed() {
        try {
            WebElement element = driver.findElement(confirmationIndicator);
            return BrowserUtils.isDisplayed(driver, element);
        } catch (Exception e) {
            return false;
        }
    }
}