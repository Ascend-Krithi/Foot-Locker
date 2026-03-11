package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By[] SEARCH_INPUT_LOCATORS = {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private final By[] SELECT_MY_STORE_LOCATORS = {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private final By[] SEARCH_BUTTON_LOCATORS = {
        By.xpath("//button[contains(text(),'Search for Stores')]"),
        By.cssSelector("button[type='submit']"),
        By.xpath("//button[contains(.,'Search')]"),
        By.cssSelector("button.search-button")
    };

    private final By[] STORE_RESULTS_LOCATORS = {
        By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']"),
        By.xpath("//div[contains(@class,'location') or contains(@class,'store')]"),
        By.cssSelector("div.store-results")
    };

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public boolean isLocationTextboxVisible() {
        try {
            WebElement element = findFirstVisible(SEARCH_INPUT_LOCATORS);
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonVisible() {
        try {
            WebElement element = findFirstVisible(SEARCH_BUTTON_LOCATORS);
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        WebElement textbox = findFirstVisible(SEARCH_INPUT_LOCATORS);
        textbox.clear();
        textbox.sendKeys(location);
    }

    public void clickSearchButton() {
        WebElement button = findFirstVisible(SEARCH_BUTTON_LOCATORS);
        try {
            button.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    public boolean areStoreResultsDisplayed() {
        try {
            WebElement element = findFirstVisible(STORE_RESULTS_LOCATORS);
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        WebElement element = findFirstVisible(SELECT_MY_STORE_LOCATORS);
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private WebElement findFirstVisible(By[] locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (TimeoutException e) {
            }
        }
        throw new NoSuchElementException("Required locator not provided in Locator Policy");
    }
}
