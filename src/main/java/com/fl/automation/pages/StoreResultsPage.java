package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private final By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By addressInsideCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private final By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private final By emptyResultsMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean areResultsDisplayed() {
        List<WebElement> cards = driver.findElements(storeCards);
        return !cards.isEmpty();
    }

    public boolean isStoreWithAddressVisible(String address) {
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(addressInsideCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().contains(address)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean setMyStoreForAddress(String address) {
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(addressInsideCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().contains(address)) {
                    List<WebElement> btns = card.findElements(setMyStoreButton);
                    if (!btns.isEmpty()) {
                        btns.get(0).click();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isConfirmationIndicatorDisplayed() {
        // Try to find an indicator that store is set, e.g., a checkmark or label
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            String cardText = card.getText();
            if (cardText.toLowerCase().contains("preferred") || cardText.toLowerCase().contains("my store") || cardText.contains("✓")) {
                return true;
            }
        }
        return false;
    }

    public boolean isStoreInHeader(String address) {
        // Try to find the address or store name in the header or location indicator
        String headerText = driver.findElement(By.tagName("header")).getText();
        return headerText.contains(address.split(",")[0]);
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMsg);
    }
}
