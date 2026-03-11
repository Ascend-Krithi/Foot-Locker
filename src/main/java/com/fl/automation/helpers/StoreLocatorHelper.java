package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    private By searchInputType = By.cssSelector("input[type='search']");
    private By searchInputName = By.cssSelector("input[name='q']");
    private By searchInputAriaLabel = By.cssSelector("input[aria-label*='Search']");
    private By searchInputPlaceholder = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");

    private By searchButton = By.xpath("//button[contains(text(),'Search for Stores')]");

    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");

    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");

    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");

    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public boolean isLocationTextboxVisible() {
        try {
            WebElement element = null;
            try {
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputType));
            } catch (Exception e1) {
                try {
                    element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputName));
                } catch (Exception e2) {
                    try {
                        element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputAriaLabel));
                    } catch (Exception e3) {
                        element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputPlaceholder));
                    }
                }
            }
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        WebElement element = null;
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputType));
        } catch (Exception e1) {
            try {
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputName));
            } catch (Exception e2) {
                try {
                    element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputAriaLabel));
                } catch (Exception e3) {
                    element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputPlaceholder));
                }
            }
        }
        element.clear();
        element.sendKeys(location);
    }

    public void clickSearchButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public boolean areStoreResultsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(storeResultCards)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}