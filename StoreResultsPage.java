package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private final WebDriver driver;

    private final By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private final By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private final By emptyResults = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean areStoreCardsDisplayed() {
        return BrowserUtils.isDisplayed(driver, storeCards);
    }

    public List<WebElement> getStoreCards() {
        return driver.findElements(storeCards);
    }

    public boolean isStoreAddressDisplayed(WebElement storeCard) {
        try {
            return storeCard.findElement(storeAddress).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getStoreAddress(WebElement storeCard) {
        try {
            return storeCard.findElement(storeAddress).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public void selectFirstStoreAsMyStore() {
        List<WebElement> cards = getStoreCards();
        if (!cards.isEmpty()) {
            WebElement card = cards.get(0);
            WebElement btn = card.findElement(setMyStoreButton);
            btn.click();
        }
    }

    public boolean isEmptyResultsDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResults);
    }
}
