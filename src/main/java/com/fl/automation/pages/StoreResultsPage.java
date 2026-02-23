package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    // Strict locator policy for store result cards
    private final By storeCards = By.cssSelector("[data-qa=location], .c-location-card, .location, [class*=location-card]");
    private final By addressInsideCard = By.cssSelector("[data-qa=address], .c-address, address, .address, [class*=address]");
    private final By setMyStoreButtonInsideCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private final By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");
    private final By confirmationIndicator = By.xpath("//*[contains(.,'preferred store') or contains(.,'saved') or contains(.,'successfully set') or contains(@class,'confirmation')]");

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isStoreCardWithAddressDisplayed(String address) {
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            try {
                WebElement addr = card.findElement(addressInsideCard);
                if (addr.getText().trim().contains(address)) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        return false;
    }

    public void setMyStoreByAddress(String address) {
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            try {
                WebElement addr = card.findElement(addressInsideCard);
                if (addr.getText().trim().contains(address)) {
                    WebElement btn = card.findElement(setMyStoreButtonInsideCard);
                    btn.click();
                    return;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Store with address not found: " + address);
    }

    public boolean isConfirmationIndicatorDisplayed() {
        return BrowserUtils.isDisplayed(driver, confirmationIndicator);
    }

    public boolean isStoreCardDisplayed() {
        return !driver.findElements(storeCards).isEmpty();
    }

    public boolean isNoStoresFoundMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMessage);
    }
}
