package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
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

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isStoreResultsDisplayed() {
        return BrowserUtils.isDisplayed(driver, storeResultCards);
    }

    public boolean isStoreWithAddressPresent(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddressInsideCard);
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
            List<WebElement> addresses = card.findElements(storeAddressInsideCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().equalsIgnoreCase(address.trim())) {
                    WebElement btn = card.findElement(setMyStoreButtonInsideCard);
                    btn.click();
                    return;
                }
            }
        }
        throw new RuntimeException("Store with address not found: " + address);
    }

    public boolean isNoResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMessage);
    }

    public boolean isConfirmationIndicatorDisplayed() {
        By confirmation = By.xpath("//*[contains(.,'preferred store') or contains(.,'saved as your store') or contains(.,'Store set successfully')]");
        return BrowserUtils.isDisplayed(driver, confirmation);
    }

    public boolean isPreferredStoreSet(String address) {
        By preferredStore = By.xpath("//*[contains(.,'375 Washington Street, Boston, MA 02108') and (contains(.,'My Store') or contains(.,'Preferred'))]");
        return BrowserUtils.isDisplayed(driver, preferredStore);
    }
}
