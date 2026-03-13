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

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    private static final By HEADER_FIND_A_STORE_LINK = By.xpath("//a[contains(@href,'store') and (contains(.,'Store') or contains(.,'store'))]");
    private static final By SELECT_MY_STORE_LINK = By.xpath("//*[contains(text(),'Select My Store')]");
    private static final By SEARCH_INPUT = By.cssSelector("input[type='search'], input[name='q']");
    private static final By SEARCH_BTN = By.xpath("//button[contains(., 'Search') or contains(.,'Search for')]");
    private static final By STORE_RESULT_CARD = By.cssSelector("[data-qa='location'], .location-card, li.store");
    private static final By SET_MY_STORE_BTN = By.xpath(".//button[contains(text(),'Set My Store')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        this.js = (JavascriptExecutor) driver;
    }

    // Basic click helper
    public void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    // Finds the header "Find a Store" link
    public WebElement findStoreLink() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(HEADER_FIND_A_STORE_LINK));
        } catch (Exception ignored) {}
        return null;
    }

    // Finds the "Select My Store" link if exists
    public WebElement findSelectMyStoreLink() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(SELECT_MY_STORE_LINK));
        } catch (Exception e) {
            return null;
        }
    }

    // Searches for a store by name
    public void searchForStore(String storeName) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        searchInput.clear();
        searchInput.sendKeys(storeName);

        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BTN));
        clickWithJsFallback(searchBtn);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(STORE_RESULT_CARD));
    }

    // Returns all store result cards
    public List<WebElement> getStoreResultCards() {
        return driver.findElements(STORE_RESULT_CARD);
    }

    // Extracts address text from a store card element
    public String getStoreAddress(WebElement storeCard) {
        try {
            return storeCard.getText(); // or customize to extract specific child elements
        } catch (Exception e) {
            return "";
        }
    }

    // Finds the "Set My Store" button inside a store card
    public WebElement findSetMyStoreButton(WebElement storeCard) {
        try {
            return storeCard.findElement(SET_MY_STORE_BTN);
        } catch (Exception e) {
            return null;
        }
    }
}
