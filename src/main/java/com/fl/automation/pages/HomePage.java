package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By[] findStoreLinkCandidates = new By[] {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    private By[] selectMyStoreCandidates = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private By[] searchInputCandidates = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButtonInCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void navigateToHomePage(String url) {
        driver.get(url);
    }

    public boolean isHomePageLoaded() {
        try {
            wait.until(ExpectedConditions.titleContains("Foot Locker"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickFindStore() {
        WebElement element = findElementWithFallback(findStoreLinkCandidates);
        clickWithJsFallback(element);
    }

    public boolean isFindStorePopupDisplayed() {
        try {
            WebElement selectMyStoreElement = findElementWithFallback(selectMyStoreCandidates);
            return selectMyStoreElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectMyStoreLinkVisible() {
        try {
            WebElement element = findElementWithFallback(selectMyStoreCandidates);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSelectMyStore() {
        WebElement element = findElementWithFallback(selectMyStoreCandidates);
        clickWithJsFallback(element);
    }

    public boolean isStoreLocatorPopupOpen() {
        try {
            WebElement searchInput = findElementWithFallback(searchInputCandidates);
            return searchInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLocationTextboxPresent() {
        try {
            WebElement element = findElementWithFallback(searchInputCandidates);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        WebElement searchInput = findElementWithFallback(searchInputCandidates);
        wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        searchInput.clear();
        searchInput.sendKeys(location);
    }

    public boolean isSearchResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResultCards));
            List<WebElement> results = driver.findElements(storeResultCards);
            return results.size() > 0;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private WebElement findElementWithFallback(By[] candidates) {
        for (By locator : candidates) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                return element;
            } catch (TimeoutException ignored) {
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }

    private void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}