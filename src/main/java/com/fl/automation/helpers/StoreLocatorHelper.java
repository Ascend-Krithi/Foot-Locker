package com.fl.automation.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {

    WebDriver driver;
    WebDriverWait wait;

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

    public StoreLocatorHelper(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void clickSelectMyStore(){
        WebElement target = null;
        for (By by : SELECT_MY_STORE_LOCATORS) {
            try {
                target = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                break;
            } catch (TimeoutException ignored) {}
        }

        if (target == null) {
            throw new TimeoutException("Could not locate 'Select My Store' control using STRICT LOCATOR POLICY.");
        }

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", target);
            wait.until(ExpectedConditions.elementToBeClickable(target)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", target);
        }
    }

    public void enterLocation(String location){
        WebElement input = null;
        for (By by : SEARCH_INPUT_LOCATORS) {
            try {
                input = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                break;
            } catch (TimeoutException ignored) {}
        }

        if (input == null) {
            throw new TimeoutException("Could not locate search input using STRICT LOCATOR POLICY.");
        }

        input.clear();
        input.sendKeys(location);
    }

    public void clickSearchForStores(){
        By searchButton = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search') or contains(.,'Find Stores')]");
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        try {
            btn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    public boolean areStoreResultsDisplayed(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(STORE_RESULT_CARDS));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isStoreAddressPresent(String address){
        try {
            List<WebElement> cards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(STORE_RESULT_CARDS));
            for (WebElement card : cards) {
                try {
                    WebElement addressElem = card.findElement(STORE_ADDRESS_INSIDE_CARD);
                    if (addressElem.getText().contains(address)) {
                        return true;
                    }
                } catch (NoSuchElementException ignored) {}
            }
            return false;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void setMyStoreByAddress(String address){
        List<WebElement> cards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(STORE_RESULT_CARDS));
        for (WebElement card : cards) {
            try {
                WebElement addressElem = card.findElement(STORE_ADDRESS_INSIDE_CARD);
                if (addressElem.getText().contains(address)) {
                    WebElement setBtn = card.findElement(SET_MY_STORE_BUTTON_INSIDE_CARD);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", setBtn);
                    try {
                        setBtn.click();
                    } catch (Exception e) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", setBtn);
                    }
                    return;
                }
            } catch (NoSuchElementException ignored) {}
        }
        throw new NoSuchElementException("Store with address '" + address + "' not found in results.");
    }

    public boolean isStoreSetAsPreferred(String storeName){
        By[] confirmationLocators = new By[] {
            By.xpath("//header//*[contains(.,'" + storeName + "')]"),
            By.xpath("//*[contains(@class,'store') or contains(@class,'location')]//*[contains(.,'" + storeName + "')]")
        };
        for (By by : confirmationLocators) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                return true;
            } catch (TimeoutException ignored) {}
        }
        return false;
    }

    public boolean isLocationTextboxPresent(){
        WebElement input = null;
        for (By by : SEARCH_INPUT_LOCATORS) {
            try {
                input = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(by));
                break;
            } catch (TimeoutException ignored) {}
        }
        return input != null;
    }

    public boolean isSearchForStoresButtonPresent(){
        By searchButton = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search') or contains(.,'Find Stores')]");
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(searchButton));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}