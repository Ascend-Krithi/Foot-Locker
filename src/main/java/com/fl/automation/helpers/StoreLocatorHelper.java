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

    private final List<By> LOCATOR_INPUT = Arrays.asList(
        By.cssSelector("input[placeholder*='Enter address' i]"),
        By.cssSelector("input[aria-label*='Location' i]")
    );

    private final List<By> LOCATOR_SEARCH_BTN = Arrays.asList(
        By.xpath("//*[self::button][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]")
    );

    private final By STORE_RESULT_CARDS = By.cssSelector("[data-qa='location']");
    private final By STORE_ADDRESS_INSIDE_CARD = By.cssSelector("[data-qa='address']");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public boolean isLocationInputDisplayed() {
        try {
            WebElement element = findElementWithFallback(LOCATOR_INPUT);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonDisplayed() {
        try {
            WebElement element = findElementWithFallback(LOCATOR_SEARCH_BTN);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        WebElement input = findElementWithFallback(LOCATOR_INPUT);
        wait.until(ExpectedConditions.elementToBeClickable(input));
        input.clear();
        input.sendKeys(location);
    }

    public void clickSearchButton() {
        WebElement button = findElementWithFallback(LOCATOR_SEARCH_BTN);
        clickWithJSFallback(button);
    }

    public void searchForStores(String location) {
        enterLocation(location);
        clickSearchButton();
        waitForStoreResults();
    }

    public void waitForStoreResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(STORE_RESULT_CARDS));
    }

    public List<WebElement> getStoreResults() {
        return driver.findElements(STORE_RESULT_CARDS);
    }

    public int getStoreResultsCount() {
        return getStoreResults().size();
    }

    public String getStoreAddress(WebElement storeCard) {
        try {
            WebElement addressElement = storeCard.findElement(STORE_ADDRESS_INSIDE_CARD);
            return addressElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean verifyStoreAddressContains(String expectedText) {
        List<WebElement> stores = getStoreResults();
        for (WebElement store : stores) {
            String address = getStoreAddress(store);
            if (address.toLowerCase().contains(expectedText.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private WebElement findElementWithFallback(List<By> locators) {
        for (By locator : locators) {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element != null) {
                    return element;
                }
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }

    private void clickWithJSFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
}