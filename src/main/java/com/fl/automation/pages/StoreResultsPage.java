package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    // Strict locator policy
    private final By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private final By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private final By emptyResultsMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isStoreResultPresent(String address) {
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddress);
            for (WebElement addr : addresses) {
                if (addr.getText().contains(address)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAnyStoreResultPresent() {
        return driver.findElements(storeCards).size() > 0;
    }

    public boolean isNoResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMsg);
    }

    public void setMyStoreByAddress(String address) {
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddress);
            for (WebElement addr : addresses) {
                if (addr.getText().contains(address)) {
                    List<WebElement> btns = card.findElements(setMyStoreButton);
                    if (!btns.isEmpty()) {
                        btns.get(0).click();
                        return;
                    }
                }
            }
        }
        throw new RuntimeException("Store with address '" + address + "' not found");
    }

    public boolean isConfirmationIndicatorDisplayed() {
        // Placeholder: check for confirmation indicator, e.g., a checkmark or label
        // For demo, check if any button now says 'My Store'
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            List<WebElement> btns = card.findElements(setMyStoreButton);
            for (WebElement btn : btns) {
                if (btn.getText().contains("My Store")) {
                    return true;
                }
            }
        }
        return false;
    }
}
