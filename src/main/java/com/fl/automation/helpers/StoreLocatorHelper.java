package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By[] SEARCH_INPUT = {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private By searchButton = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search') or contains(.,'Find Stores')]");
    private By storeResults = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public boolean isLocationTextboxVisible() {
        try {
            WebElement element = waitForFirstVisible(SEARCH_INPUT);
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
        WebElement element = waitForFirstVisible(SEARCH_INPUT);
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
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResults));
            return driver.findElements(storeResults).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement waitForFirstVisible(By[] locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (TimeoutException e) {
            }
        }
        throw new TimeoutException("None of the provided locators found element");
    }
}