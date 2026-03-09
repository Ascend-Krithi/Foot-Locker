package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;
import org.openqa.selenium.WebElement;

public class StoreResultsPage {
    private WebDriver driver;
    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddressInCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isStoreResultDisplayed() {
        return BrowserUtils.isDisplayed(driver, storeResultCards);
    }

    public boolean isStoreAddressPresent(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddressInCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().equalsIgnoreCase(address.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setMyStoreByAddress(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddressInCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().equalsIgnoreCase(address.trim())) {
                    WebElement btn = card.findElement(setMyStoreButton);
                    btn.click();
                    return;
                }
            }
        }
    }

    public boolean isSetMyStoreButtonPresent(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddressInCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().equalsIgnoreCase(address.trim())) {
                    return !card.findElements(setMyStoreButton).isEmpty();
                }
            }
        }
        return false;
    }
}
