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

    private static final By COOKIE_ACCEPT_1 = By.id("onetrust-accept-btn-handler");
    private static final By COOKIE_ACCEPT_2 = By.cssSelector("button#onetrust-accept-btn-handler");
    private static final By COOKIE_ACCEPT_3 = By.cssSelector("button[aria-label*='Accept' i]");
    private static final By COOKIE_ACCEPT_4 = By.xpath("//button[contains(normalize-space(.),'Accept All Cookies')]");

    private static final By MODAL_CLOSE_1 = By.cssSelector("button[aria-label='Close']");
    private static final By MODAL_CLOSE_2 = By.xpath("//button[@aria-label='Close' or contains(@class,'close')]");

    private static final By HEADER_FIND_A_STORE_1 = By.linkText("Find a Store");
    private static final By HEADER_FIND_A_STORE_2 = By.cssSelector("header a[href*='stores.footlocker.com']");
    private static final By HEADER_FIND_A_STORE_3 = By.xpath("//header//a[contains(.,'Find a Store')]");

    private static final By SELECT_MY_STORE_1 = By.xpath("//a[contains(.,'Select My Store')]");
    private static final By SELECT_MY_STORE_2 = By.xpath("//button[contains(.,'Select My Store')]");

    private static final By SEARCH_INPUT_1 = By.cssSelector("input[type='search']");
    private static final By SEARCH_INPUT_2 = By.cssSelector("input[name='q']");
    private static final By SEARCH_INPUT_3 = By.cssSelector("input[aria-label*='Search']");

    private static final By LOCATOR_SEARCH_BTN_1 = By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]");
    private static final By LOCATOR_SEARCH_BTN_2 = By.cssSelector("[aria-label*='Search for store' i]");

    private static final By STORE_RESULT_CARDS_1 = By.cssSelector("[data-qa='location']");
    private static final By STORE_RESULT_CARDS_2 = By.cssSelector(".c-location-card");

    private static final By STORE_ADDRESS_INSIDE_CARD_1 = By.cssSelector("[data-qa='address']");
    private static final By STORE_ADDRESS_INSIDE_CARD_2 = By.cssSelector(".c-address");

    private static final By SET_MY_STORE_BUTTON_INSIDE_CARD = By.xpath(".//button[contains(.,'Set My Store')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        this.js = (JavascriptExecutor) driver;
    }

    public void handleCookieConsent() {
        try {
            WebElement cookieButton = findElementWithFallback(
                COOKIE_ACCEPT_1, COOKIE_ACCEPT_2, COOKIE_ACCEPT_3, COOKIE_ACCEPT_4
            );
            if (cookieButton != null) {
                clickWithJsFallback(cookieButton);
                Thread.sleep(500);
            }
        } catch (Exception e) {
        }
    }

    public void closeModalIfPresent() {
        try {
            WebElement closeButton = findElementWithFallback(MODAL_CLOSE_1, MODAL_CLOSE_2);
            if (closeButton != null) {
                clickWithJsFallback(closeButton);
                Thread.sleep(500);
            }
        } catch (Exception e) {
        }
    }

    public WebElement findStoreLink() {
        return findElementWithFallback(
            HEADER_FIND_A_STORE_1, HEADER_FIND_A_STORE_2, HEADER_FIND_A_STORE_3
        );
    }

    public WebElement findSelectMyStoreLink() {
        return findElementWithFallback(SELECT_MY_STORE_1, SELECT_MY_STORE_2);
    }

    public WebElement findSearchInput() {
        return findElementWithFallback(SEARCH_INPUT_1, SEARCH_INPUT_2, SEARCH_INPUT_3);
    }

    public WebElement findSearchButton() {
        return findElementWithFallback(LOCATOR_SEARCH_BTN_1, LOCATOR_SEARCH_BTN_2);
    }

    public List<WebElement> findStoreResultCards() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(STORE_RESULT_CARDS_1));
            return driver.findElements(STORE_RESULT_CARDS_1);
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(STORE_RESULT_CARDS_2));
                return driver.findElements(STORE_RESULT_CARDS_2);
            } catch (Exception ex) {
                return List.of();
            }
        }
    }

    public String getStoreAddress(WebElement storeCard) {
        try {
            WebElement addressElement = storeCard.findElement(STORE_ADDRESS_INSIDE_CARD_1);
            return addressElement.getText();
        } catch (Exception e) {
            try {
                WebElement addressElement = storeCard.findElement(STORE_ADDRESS_INSIDE_CARD_2);
                return addressElement.getText();
            } catch (Exception ex) {
                return "";
            }
        }
    }

    public WebElement findSetMyStoreButton(WebElement storeCard) {
        try {
            return storeCard.findElement(SET_MY_STORE_BUTTON_INSIDE_CARD);
        } catch (Exception e) {
            return null;
        }
    }

    private WebElement findElementWithFallback(By... locators) {
        for (By locator : locators) {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element != null && element.isDisplayed()) {
                    return element;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    public void searchForStore(String location) {
        WebElement searchInput = findSearchInput();
        if (searchInput != null) {
            searchInput.clear();
            searchInput.sendKeys(location);
            WebElement searchButton = findSearchButton();
            if (searchButton != null) {
                clickWithJsFallback(searchButton);
            }
        }
    }
}