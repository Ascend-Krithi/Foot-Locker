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
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private By locationTextbox = By.id("StoreLocator_search_query");
    private By locationInput = By.cssSelector("input[placeholder*='City, State']");
    private By searchButton = By.cssSelector("button[data-testid='store-search-button']");
    private By searchForStoresButton = By.xpath("//button[contains(text(),'Search for Stores')]");
    private By storeResults = By.cssSelector("div[data-testid='store-result']");
    private By storeResultsList = By.cssSelector("div.store-list");
    private By storeLocatorContainer = By.id("store-locator-container");
    private By storeAddresses = By.cssSelector("div.store-address");
    private By storeAddressText = By.xpath("//div[contains(@class,'store')]//span[contains(@class,'address')]");
    private By setMyStoreButtons = By.xpath("//button[contains(text(),'Set My Store')]");
    private By storeNameHeader = By.cssSelector("header .store-name");
    private By selectedStoreIndicator = By.xpath("//*[contains(@class,'header') or contains(@class,'Header')]//*[contains(@class,'store')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        this.js = (JavascriptExecutor) driver;
    }

    public void waitForStoreLocatorToLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeLocatorContainer));
        } catch (Exception e) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox));
        }
    }

    public boolean isStoreLocatorModalVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox));
            input.clear();
            input.sendKeys(location);
        } catch (Exception e) {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
            input.clear();
            input.sendKeys(location);
        }
    }

    public void clickSearch() {
        try {
            WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            search.click();
        } catch (Exception e) {
            try {
                WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchForStoresButton));
                js.executeScript("arguments[0].click();", search);
            } catch (Exception ex) {
                throw new RuntimeException("Unable to click search button");
            }
        }
    }

    public void searchForStores(String location) {
        enterLocation(location);
        clickSearch();
        waitForStoreResults();
    }

    public void waitForStoreResults() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResults));
        } catch (Exception e) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResultsList));
        }
    }

    public boolean isStoreInResults(String storeAddress) {
        try {
            List<WebElement> addresses = driver.findElements(storeAddresses);
            for (WebElement element : addresses) {
                if (element.getText().contains(storeAddress)) {
                    return true;
                }
            }
            List<WebElement> addressTexts = driver.findElements(storeAddressText);
            for (WebElement element : addressTexts) {
                if (element.getText().contains(storeAddress)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void selectStore(String storeAddress) {
        clickSetMyStore(storeAddress);
    }

    public void clickSetMyStore(String storeAddress) {
        try {
            List<WebElement> addresses = driver.findElements(storeAddresses);
            for (int i = 0; i < addresses.size(); i++) {
                if (addresses.get(i).getText().contains(storeAddress)) {
                    List<WebElement> buttons = driver.findElements(setMyStoreButtons);
                    if (i < buttons.size()) {
                        js.executeScript("arguments[0].click();", buttons.get(i));
                        return;
                    }
                }
            }
            List<WebElement> buttons = driver.findElements(setMyStoreButtons);
            if (!buttons.isEmpty()) {
                js.executeScript("arguments[0].click();", buttons.get(0));
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to click Set My Store button for: " + storeAddress);
        }
    }

    public boolean isStoreSelected(String storeAddress) {
        try {
            Thread.sleep(2000);
            return isStoreDisplayedInHeader(storeAddress);
        } catch (Exception e) {
            return false;
        }
    }

    public String getSelectedStoreFromHeader() {
        try {
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(storeNameHeader));
            return header.getText();
        } catch (Exception e) {
            try {
                WebElement indicator = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedStoreIndicator));
                return indicator.getText();
            } catch (Exception ex) {
                return "";
            }
        }
    }

    public boolean isStoreDisplayedInHeader(String expectedStore) {
        try {
            String headerText = getSelectedStoreFromHeader();
            return headerText.contains(expectedStore);
        } catch (Exception e) {
            return false;
        }
    }
}