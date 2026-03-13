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

    // =======================
    // Core Click Helper
    // =======================
    public void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    // =======================
    // Find Elements Methods (tests expect these exact names)
    // =======================

    // Find header link "Find a Store"
    public WebElement findStoreLink() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(HEADER_FIND_A_STORE_LINK));
    }

    // Find "Select My Store" link
    public WebElement findSelectMyStoreLink() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(SELECT_MY_STORE_LINK));
        } catch (Exception e) {
            return null;
        }
    }

    // Search input field (matches your tests)
    public WebElement findSearchInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
    }

    // Search button (matches your tests)
    public WebElement findSearchButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BTN));
    }

    // Store result cards (matches your tests)
    public List<WebElement> findStoreResultCards() {
        return driver.findElements(STORE_RESULT_CARD);
    }

    // Search for a store by name (alternative usage)
    public void searchForStore(String storeName) {
        WebElement input = findSearchInput();
        input.clear();
        input.sendKeys(storeName);
        clickWithJsFallback(findSearchButton());
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(STORE_RESULT_CARD));
    }

    // Get store address from a card
    public String getStoreAddress(WebElement storeCard) {
        try {
            return storeCard.getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Find "Set My Store" button inside a store card
    public WebElement findSetMyStoreButton(WebElement storeCard) {
        try {
            return storeCard.findElement(SET_MY_STORE_BTN);
        } catch (Exception e) {
            return null;
        }
    }
}
