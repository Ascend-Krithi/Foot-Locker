package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import com.fl.automation.core.BrowserUtils;

public class StoreResultsPage {
    private WebDriver driver;

    private By[] storeResultCardLocators = new By[] {
        By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']")
    };

    private By[] storeAddressLocators = new By[] {
        By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']")
    };

    private By[] setMyStoreButtonLocators = new By[] {
        By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]")
    };

    private By[] emptyResultsMessageLocators = new By[] {
        By.xpath("//*[contains(.,'There are no locations in your search area')]")
    };

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getStoreResultCards() {
        for (By locator : storeResultCardLocators) {
            List<WebElement> cards = driver.findElements(locator);
            if (!cards.isEmpty()) return cards;
        }
        throw new RuntimeException("Store result cards not found");
    }

    public WebElement getStoreAddress(WebElement card) {
        for (By locator : storeAddressLocators) {
            try {
                WebElement el = card.findElement(locator);
                if (el.isDisplayed()) return el;
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Store address not found in card");
    }

    public WebElement getSetMyStoreButton(WebElement card) {
        for (By locator : setMyStoreButtonLocators) {
            try {
                WebElement el = card.findElement(locator);
                if (el.isDisplayed()) return el;
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Set My Store button not found in card");
    }

    public boolean isEmptyResultsMessageDisplayed() {
        for (By locator : emptyResultsMessageLocators) {
            try {
                WebElement el = driver.findElement(locator);
                if (el.isDisplayed()) return true;
            } catch (Exception ignored) {}
        }
        return false;
    }
}