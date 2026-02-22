package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import com.fl.automation.core.BrowserUtils;

public class StoreResultsPage {
    private WebDriver driver;
    private By storeResultCards = By.cssSelector("[data-qa=location], .c-location-card, .location, [class*=location-card]");
    private By storeAddressInsideCard = By.cssSelector("[data-qa=address], .c-address, address, .address, [class*=address]");
    private By setMyStoreButtonInsideCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By selectMyStoreButton = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getStoreCardsCount() {
        return driver.findElements(storeResultCards).size();
    }

    public String getFirstStoreAddress() {
        List<WebElement> cards = driver.findElements(storeResultCards);
        if (!cards.isEmpty()) {
            WebElement address = cards.get(0).findElement(storeAddressInsideCard);
            return BrowserUtils.getText(driver, address);
        }
        return null;
    }

    public void setFirstStoreAsMyStore() {
        List<WebElement> cards = driver.findElements(storeResultCards);
        if (!cards.isEmpty()) {
            WebElement btn = cards.get(0).findElement(setMyStoreButtonInsideCard);
            BrowserUtils.click(driver, btn);
        } else {
            throw new RuntimeException("No store cards found");
        }
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return driver.findElements(emptyResultsMessage).size() > 0 &&
                BrowserUtils.isDisplayed(driver, driver.findElement(emptyResultsMessage));
    }

    public void selectMyStoreFromHeader() {
        if (driver.findElements(selectMyStoreLink).size() > 0) {
            BrowserUtils.click(driver, driver.findElement(selectMyStoreLink));
        } else if (driver.findElements(selectMyStoreButton).size() > 0) {
            BrowserUtils.click(driver, driver.findElement(selectMyStoreButton));
        } else {
            throw new RuntimeException("Select My Store not found");
        }
    }
}