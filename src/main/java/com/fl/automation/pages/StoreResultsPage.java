package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddressInsideCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButtonInsideCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isStoreResultsDisplayed() {
        return driver.findElements(storeResultCards).size() > 0;
    }

    public boolean isStoreAddressPresent(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddressInsideCard);
            for (WebElement addr : addresses) {
                if (addr.getText().contains(address)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean setMyStoreByAddress(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddressInsideCard);
            for (WebElement addr : addresses) {
                if (addr.getText().contains(address)) {
                    List<WebElement> btns = card.findElements(setMyStoreButtonInsideCard);
                    if (!btns.isEmpty()) {
                        btns.get(0).click();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isNoStoresFoundMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMessage);
    }
}
