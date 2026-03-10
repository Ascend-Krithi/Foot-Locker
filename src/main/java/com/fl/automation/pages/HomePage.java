package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait waitShort;
    private final WebDriverWait wait;

    private final By[] FIND_STORE_LOCATORS = new By[] {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };

    private final By[] SELECT_MY_STORE_LOCATORS = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private final By[] SEARCH_INPUT_LOCATORS = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private final By STORE_RESULT_CARDS = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By STORE_ADDRESS_INSIDE_CARD = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private final By SET_MY_STORE_BUTTON_INSIDE_CARD = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private final By EMPTY_RESULTS_MESSAGE = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.waitShort = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void clickFindStore() {
        WebElement target = findElementWithFallback(FIND_STORE_LOCATORS, wait);
        clickWithJsFallback(target);
    }

    public void clickSelectMyStore() {
        WebElement target = findElementWithFallback(SELECT_MY_STORE_LOCATORS, wait);
        clickWithJsFallback(target);
    }

    public void enterLocation(String location) {
        WebElement searchInput = findElementWithFallback(SEARCH_INPUT_LOCATORS, wait);
        searchInput.clear();
        searchInput.sendKeys(location);
    }

    public void clickSearchButton() {
        By searchButtonLocator = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search')]");
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
        clickWithJsFallback(searchButton);
    }

    public boolean isSelectMyStoreVisible() {
        try {
            findElementWithFallback(SELECT_MY_STORE_LOCATORS, waitShort);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchInputVisible() {
        try {
            findElementWithFallback(SEARCH_INPUT_LOCATORS, waitShort);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonVisible() {
        try {
            By searchButtonLocator = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search')]");
            waitShort.until(ExpectedConditions.visibilityOfElementLocated(searchButtonLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areStoreResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(STORE_RESULT_CARDS));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement findStoreByAddress(String address) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(STORE_RESULT_CARDS));
        List<WebElement> storeCards = driver.findElements(STORE_RESULT_CARDS);
        
        for (WebElement card : storeCards) {
            try {
                WebElement addressElement = card.findElement(STORE_ADDRESS_INSIDE_CARD);
                if (addressElement.getText().contains(address)) {
                    return card;
                }
            } catch (NoSuchElementException ignored) {
            }
        }
        throw new NoSuchElementException("Store with address '" + address + "' not found");
    }

    public void clickSetMyStoreForCard(WebElement storeCard) {
        WebElement setButton = storeCard.findElement(SET_MY_STORE_BUTTON_INSIDE_CARD);
        clickWithJsFallback(setButton);
    }

    public boolean isStoreSetInHeader(String storeName) {
        try {
            By headerStoreLocator = By.xpath("//header//*[contains(.,'" + storeName + "')]");
            waitShort.until(ExpectedConditions.visibilityOfElementLocated(headerStoreLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPopupMessageVisible(String message) {
        try {
            By messageLocator = By.xpath("//*[contains(.,'" + message + "')]");
            waitShort.until(ExpectedConditions.visibilityOfElementLocated(messageLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToProductPage() {
        try {
            By productLinkLocator = By.xpath("//a[contains(@href,'/product') or contains(@href,'/p/')]");
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(productLinkLocator));
            clickWithJsFallback(productLink);
        } catch (Exception e) {
            driver.get("https://www.footlocker.com/category/mens/shoes.html");
        }
    }

    public void navigateToHomePage() {
        driver.get("https://www.footlocker.com");
    }

    private WebElement findElementWithFallback(By[] locators, WebDriverWait waitToUse) {
        for (By locator : locators) {
            try {
                return waitToUse.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (Exception ignored) {
            }
        }
        throw new TimeoutException("Could not locate element using provided fallback locators");
    }

    private void clickWithJsFallback(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}