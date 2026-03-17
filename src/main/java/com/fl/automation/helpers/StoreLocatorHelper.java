package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    private By[] locationSearchInputLocators = {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i]")
    };

    private By[] searchButtonLocators = {
        By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]"),
        By.cssSelector("[aria-label*='Search for store' i]"),
        By.cssSelector("button[type='submit']")
    };

    private By[] storeResultCardsLocators = {
        By.cssSelector("[data-qa='location']"),
        By.cssSelector(".c-location-card"),
        By.cssSelector(".location"),
        By.cssSelector("[class*='location-card']")
    };

    private By[] storeAddressLocators = {
        By.cssSelector("[data-qa='address']"),
        By.cssSelector(".c-address"),
        By.cssSelector("address"),
        By.cssSelector(".address"),
        By.cssSelector("[class*='address']")
    };

    private By setMyStoreButtonLocator = By.xpath(".//button[contains(.,'Set My Store')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public boolean isLocationSearchInputDisplayed() {
        try {
            WebElement locationInput = findElementWithFallback(locationSearchInputLocators);
            return wait.until(ExpectedConditions.visibilityOf(locationInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            WebElement searchButton = findElementWithFallback(searchButtonLocators);
            return searchButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        WebElement locationInput = findElementWithFallback(locationSearchInputLocators);
        wait.until(ExpectedConditions.visibilityOf(locationInput));
        locationInput.clear();
        locationInput.sendKeys(location);
    }

    public void clickSearchButton() {
        WebElement searchButton = findElementWithFallback(searchButtonLocators);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        try {
            searchButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchButton);
        }
    }

    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> storeCards = findElementsWithFallback(storeResultCardsLocators);
            return storeCards.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSpecificStoreDisplayed(String address) {
        try {
            List<WebElement> storeCards = findElementsWithFallback(storeResultCardsLocators);
            for (WebElement card : storeCards) {
                WebElement addressElement = findAddressInCard(card);
                if (addressElement != null && addressElement.getText().contains(address)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSetMyStoreForAddress(String address) {
        List<WebElement> storeCards = findElementsWithFallback(storeResultCardsLocators);
        for (WebElement card : storeCards) {
            WebElement addressElement = findAddressInCard(card);
            if (addressElement != null && addressElement.getText().contains(address)) {
                WebElement setMyStoreButton = card.findElement(setMyStoreButtonLocator);
                wait.until(ExpectedConditions.elementToBeClickable(setMyStoreButton));
                try {
                    setMyStoreButton.click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", setMyStoreButton);
                }
                return;
            }
        }
        throw new RuntimeException("Store with address " + address + " not found");
    }

    public boolean isStoreConfirmationDisplayed() {
        try {
            wait.until(ExpectedConditions.urlContains("footlocker.com"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement findElementWithFallback(By[] locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }

    private List<WebElement> findElementsWithFallback(By[] locators) {
        for (By locator : locators) {
            try {
                List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
                if (elements.size() > 0) {
                    return elements;
                }
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Elements not found with any of the provided locators");
    }

    private WebElement findAddressInCard(WebElement card) {
        for (By locator : storeAddressLocators) {
            try {
                return card.findElement(locator);
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }
}