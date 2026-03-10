package com.fl.automation.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {

    WebDriver driver;
    WebDriverWait wait;

    private final By[] searchInputLocators = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private final By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");

    private final By storeAddressInCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");

    private final By setMyStoreButtonInCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");

    private final By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public WebElement getLocationTextbox() {
        WebElement target = null;
        for (By by : searchInputLocators) {
            try {
                target = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                break;
            } catch (TimeoutException ignored) {}
        }
        if (target == null) {
            throw new TimeoutException("Could not locate search input using policy locators.");
        }
        return target;
    }

    public void enterLocation(String location) {
        WebElement input = getLocationTextbox();
        input.clear();
        input.sendKeys(location);
    }

    public void clickSearchForStores() {
        By[] searchButtonLocators = new By[] {
            By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search') or contains(.,'Find Stores')]"),
            By.cssSelector("button[type='submit']")
        };

        WebElement target = null;
        for (By by : searchButtonLocators) {
            try {
                target = wait.until(ExpectedConditions.elementToBeClickable(by));
                break;
            } catch (TimeoutException ignored) {}
        }

        if (target == null) {
            throw new TimeoutException("Could not locate 'Search for Stores' button.");
        }

        try {
            target.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", target);
        }
    }

    public List<WebElement> getStoreResults() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(storeResultCards));
    }

    public WebElement findStoreByAddress(String address) {
        List<WebElement> stores = getStoreResults();
        for (WebElement store : stores) {
            try {
                WebElement addressElem = store.findElement(storeAddressInCard);
                if (addressElem.getText().contains(address)) {
                    return store;
                }
            } catch (NoSuchElementException ignored) {}
        }
        throw new NoSuchElementException("Store with address '" + address + "' not found.");
    }

    public void clickSetMyStoreForAddress(String address) {
        WebElement storeCard = findStoreByAddress(address);
        WebElement setBtn = storeCard.findElement(setMyStoreButtonInCard);
        try {
            setBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", setBtn);
        }
    }

    public boolean isEmptyResultsMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(emptyResultsMessage)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isStoreResultsDisplayed() {
        try {
            List<WebElement> results = getStoreResults();
            return results != null && !results.isEmpty();
        } catch (TimeoutException e) {
            return false;
        }
    }
}