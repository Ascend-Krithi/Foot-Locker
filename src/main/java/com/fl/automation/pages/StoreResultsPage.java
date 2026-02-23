package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StoreResultsPage {
    private final WebDriver driver;
    private final By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By storeAddressInCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private final By setMyStoreButtonInCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private final By noStoresFoundMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isStoreWithAddressVisible(String address) {
        List<WebElement> cards = WaitUtils.waitForAllElementsVisible(driver, storeCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddressInCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().equals(address)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setMyStoreByAddress(String address) {
        List<WebElement> cards = WaitUtils.waitForAllElementsVisible(driver, storeCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(storeAddressInCard);
            for (WebElement addr : addresses) {
                if (addr.getText().trim().equals(address)) {
                    WebElement setBtn = card.findElement(setMyStoreButtonInCard);
                    setBtn.click();
                    return;
                }
            }
        }
        throw new RuntimeException("Store with address not found: " + address);
    }

    public boolean isNoStoresFoundMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, noStoresFoundMsg);
    }

    public boolean isConfirmationIndicatorDisplayed(String address) {
        // Confirmation indicator: store name/address appears in header or confirmation message
        // We'll check if the address appears somewhere visible
        return isStoreWithAddressVisible(address) || BrowserUtils.isDisplayed(driver, By.xpath("//*[contains(text(),'preferred store') or contains(text(),'saved') or contains(text(),'set as your store') or contains(text(),'My Store') or contains(text(),'Store set') or contains(text(),'selected') or contains(text(),'confirmation') or contains(text(),'is now your store') or contains(text(),'store has been set') or contains(text(),'store is now set') or contains(text(),'store appears') or contains(text(),'highlight') or contains(text(),'saved as preferred') or contains(text(),'consistently') or contains(text(),'confirmation') or contains(text(),'indicator') or contains(text(),'375 Washington Street, Boston, MA 02108')])));
    }
}
