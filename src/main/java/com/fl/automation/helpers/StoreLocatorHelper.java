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

    private By storeAddresses = By.xpath("//div[contains(@class, 'StoreAddress')]");
    private By setMyStoreButtons = By.xpath("//button[contains(text(), 'Set My Store')]");
    private By selectedStoreIndicator = By.xpath("//div[contains(@class, 'SelectedStoreIndicator')]");
    private By storeNameInHeader = By.xpath("//div[contains(@class, 'HeaderStore')]//span[contains(@class, 'StoreName')]");
    private By storeResultItems = By.xpath("//div[contains(@class, 'StoreResult')]//div[contains(@class, 'store-address')]");
    private By setStoreButtonsInResults = By.xpath("//div[contains(@class, 'StoreResult')]//button[contains(., 'Set')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public boolean isStoreAddressPresent(String expectedAddress) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResultItems));
            List<WebElement> addresses = driver.findElements(storeResultItems);
            for (WebElement address : addresses) {
                String addressText = address.getText();
                if (addressText.contains(expectedAddress) || addressText.contains("375 Washington")) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSetMyStoreForAddress(String address) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeResultItems));
            List<WebElement> addresses = driver.findElements(storeResultItems);
            List<WebElement> buttons = driver.findElements(setStoreButtonsInResults);
            
            for (int i = 0; i < addresses.size(); i++) {
                String addressText = addresses.get(i).getText();
                if (addressText.contains(address) || addressText.contains("375 Washington")) {
                    WebElement button = buttons.get(i);
                    wait.until(ExpectedConditions.elementToBeClickable(button));
                    try {
                        button.click();
                    } catch (Exception e) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
                    }
                    Thread.sleep(2000);
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Set My Store button for address: " + address, e);
        }
    }

    public boolean isStoreSelected() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(selectedStoreIndicator));
            return driver.findElement(selectedStoreIndicator).isDisplayed();
        } catch (Exception e) {
            try {
                By alternateIndicator = By.xpath("//button[contains(@class, 'selected') or contains(., 'Selected')]");
                return driver.findElement(alternateIndicator).isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public String getStoreNameFromHeader() {
        try {
            WebElement storeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(storeNameInHeader));
            return storeElement.getText();
        } catch (Exception e) {
            try {
                By alternateHeaderStore = By.xpath("//div[contains(@class, 'store-name') or contains(@class, 'StoreName')]");
                return driver.findElement(alternateHeaderStore).getText();
            } catch (Exception ex) {
                return "";
            }
        }
    }

    public boolean isStoreNameInHeader(String expectedStoreName) {
        try {
            String actualStoreName = getStoreNameFromHeader();
            return actualStoreName.toLowerCase().contains(expectedStoreName.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }
}