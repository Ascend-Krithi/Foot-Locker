package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class StoreLocatorHelper {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final List<By> LOCATOR_INPUT = Arrays.asList(
            By.cssSelector("input[placeholder*='Enter address']"),
            By.cssSelector("input[aria-label*='Location']"),
            By.cssSelector("input[name*='location']"),
            By.xpath("//input[contains(@placeholder,'address') or contains(@placeholder,'location')]")
    );

    private static final List<By> LOCATOR_SEARCH_BTN = Arrays.asList(
            By.xpath("//*[self::button or self::a][contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search')]"),
            By.cssSelector("button[type='submit']"),
            By.cssSelector("button[class*='search']"),
            By.xpath("//button[contains(.,'Search')]"),
            By.xpath("//button[contains(.,'Find')]")
    );

    private static final List<By> STORE_RESULT_CARDS = Arrays.asList(
            By.cssSelector("[data-qa='location']"),
            By.cssSelector("div[class*='store-card']"),
            By.cssSelector("div[class*='location-card']"),
            By.xpath("//div[contains(@class,'store') and contains(@class,'result')]")
    );

    private static final List<By> STORE_ADDRESS_INSIDE_CARD = Arrays.asList(
            By.cssSelector("[data-qa='address']"),
            By.cssSelector("span[class*='address']"),
            By.cssSelector("div[class*='address']"),
            By.xpath(".//span[contains(@class,'address')]")
    );

    private static final List<By> SET_STORE_BUTTON = Arrays.asList(
            By.xpath("//button[contains(.,'Set as My Store')]"),
            By.xpath("//a[contains(.,'Set as My Store')]"),
            By.cssSelector("button[class*='set-store']"),
            By.xpath("//button[contains(.,'Select This Store')]")
    );

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public boolean isLocationInputDisplayed() {
        try {
            WebElement input = findElementWithFallback(LOCATOR_INPUT);
            return input.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            WebElement button = findElementWithFallback(LOCATOR_SEARCH_BTN);
            return button.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        WebElement input = findElementWithFallback(LOCATOR_INPUT);
        input.clear();
        input.sendKeys(location);
    }

    public void clickSearchButton() {
        WebElement button = findElementWithFallback(LOCATOR_SEARCH_BTN);
        clickWithJsFallback(button);
    }

    public void searchForStores(String location) {
        enterLocation(location);
        clickSearchButton();
        waitForResults();
    }

    public void waitForResults() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(STORE_RESULT_CARDS.get(0)));
    }

    public List<WebElement> getStoreResults() {
        return driver.findElements(STORE_RESULT_CARDS.get(0));
    }

    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> results = getStoreResults();
            return results != null && results.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public String getStoreAddress(WebElement storeCard) {
        for (By locator : STORE_ADDRESS_INSIDE_CARD) {
            try {
                WebElement addressElement = storeCard.findElement(locator);
                return addressElement.getText();
            } catch (Exception e) {
                continue;
            }
        }
        return "";
    }

    public boolean verifyStoreAddressContains(String expectedAddress) {
        List<WebElement> stores = getStoreResults();
        for (WebElement store : stores) {
            String address = getStoreAddress(store);
            if (address.contains(expectedAddress)) {
                return true;
            }
        }
        return false;
    }

    public void setPreferredStore(String storeAddress) {
        List<WebElement> stores = getStoreResults();
        for (WebElement store : stores) {
            String address = getStoreAddress(store);
            if (address.contains(storeAddress)) {
                WebElement setStoreBtn = findElementInParent(store, SET_STORE_BUTTON);
                clickWithJsFallback(setStoreBtn);
                return;
            }
        }
        throw new RuntimeException("Store with address " + storeAddress + " not found");
    }

    private WebElement findElementWithFallback(List<By> locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }

    private WebElement findElementInParent(WebElement parent, List<By> locators) {
        for (By locator : locators) {
            try {
                return parent.findElement(locator);
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found in parent with any of the provided locators");
    }

    private void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}