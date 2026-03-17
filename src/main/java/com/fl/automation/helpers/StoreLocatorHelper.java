package com.fl.automation.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    // ===== LOCATORS =====

    private By findStoreButton = By.xpath("//a[contains(@href,'store-locator') or contains(text(),'Find a Store')]");
    private By storePopupHeader = By.xpath("//*[contains(text(),'Find a Store') or contains(text(),'Store Locator')]");
    private By locationSearchInput = By.xpath("//input[contains(@placeholder,'address') or contains(@placeholder,'city')]");
    private By searchButton = By.xpath("//button[contains(text(),'Search')]");
    private By storeCards = By.xpath("//*[contains(@class,'store') and contains(@class,'card')]");
    private By storeAddress = By.xpath(".//address");
    private By setMyStoreButton = By.xpath(".//button[contains(text(),'Set')]");
    private By storeConfirmationBanner = By.xpath("//*[contains(text(),'My Store')]");

    // ===== BASIC ACTIONS =====

    public void openStoreLocator() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(findStoreButton));
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(storePopupHeader));
    }

    public void waitForStoreLocatorToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locationSearchInput));
    }

    public boolean isLocationSearchInputDisplayed() {
        return driver.findElement(locationSearchInput).isDisplayed();
    }

    public boolean isSearchButtonDisplayed() {
        return driver.findElement(searchButton).isDisplayed();
    }

    public void enterLocation(String location) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationSearchInput));
        input.clear();
        input.sendKeys(location);
    }

    public void clickSearchButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
    }

    public boolean areStoreResultsDisplayed() {
        List<WebElement> cards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(storeCards));
        return cards.size() > 0;
    }

    public boolean isSpecificStoreDisplayed(String addressText) {
        List<WebElement> cards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(storeCards));
        for (WebElement card : cards) {
            try {
                String text = card.findElement(storeAddress).getText();
                if (text.contains(addressText)) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }

    public void clickSetMyStoreForAddress(String addressText) {
        List<WebElement> cards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(storeCards));
        for (WebElement card : cards) {
            try {
                String text = card.findElement(storeAddress).getText();
                if (text.contains(addressText)) {
                    card.findElement(setMyStoreButton).click();
                    return;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Store not found: " + addressText);
    }

    public boolean isStoreConfirmationDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(storeConfirmationBanner)).isDisplayed();
    }
}
