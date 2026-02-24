package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By addressInsideCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isStoreResultsDisplayed() {
        return driver.findElements(storeCards).size() > 0;
    }

    public boolean isStoreAddressPresent(String address) {
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

    public boolean setMyStoreByAddress(String address) {
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

    public boolean isConfirmationIndicatorDisplayed(String address) {
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(addressInsideCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().contains(address)) {
                    String cardText = card.getText();
                    if (cardText.contains("My Store") || cardText.contains("Preferred Store") || cardText.contains("Saved")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMsg);
    }
}
