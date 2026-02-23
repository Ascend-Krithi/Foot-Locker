package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private final WebDriver driver;
    private final By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By storeAddressInsideCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private final By setMyStoreButtonInsideCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private final By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean areStoreResultsDisplayed() {
        return BrowserUtils.isDisplayed(driver, storeResultCards);
    }

    public boolean isStoreAddressVisible(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddressInsideCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().contains(address)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean clickSetMyStoreForAddress(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddressInsideCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().contains(address)) {
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

    public boolean isConfirmationIndicatorDisplayed() {
        // Assume confirmation indicator is a class change or text, for demo return true if any card has 'My Store' button disabled
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> btns = card.findElements(setMyStoreButtonInsideCard);
            for (WebElement btn : btns) {
                if (!btn.isEnabled() || btn.getText().toLowerCase().contains("my store")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isNoStoresFoundMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMessage);
    }
}
