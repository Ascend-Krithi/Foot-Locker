package com.fl.automation.pages;

import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private final By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By storeAddressInsideCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private final By setMyStoreButtonInsideCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private final By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getStoreCards() {
        return WaitUtils.waitForElementsVisible(driver, storeResultCards);
    }

    public List<String> getStoreAddresses() {
        List<String> addresses = new ArrayList<>();
        for (WebElement card : getStoreCards()) {
            try {
                WebElement address = card.findElement(storeAddressInsideCard);
                addresses.add(com.fl.automation.core.BrowserUtils.getText(address));
            } catch (Exception ignored) {}
        }
        return addresses;
    }

    public boolean isEmptyResultsDisplayed() {
        try {
            WebElement msg = WaitUtils.waitForElementVisible(driver, emptyResultsMessage);
            return com.fl.automation.core.BrowserUtils.isDisplayed(msg);
        } catch (Exception e) {
            return false;
        }
    }

    public void setMyStoreOnFirstCard() {
        List<WebElement> cards = getStoreCards();
        if (!cards.isEmpty()) {
            WebElement btn = cards.get(0).findElement(setMyStoreButtonInsideCard);
            com.fl.automation.core.BrowserUtils.click(btn);
        }
    }
}