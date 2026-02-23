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
    private By setMyStoreButtonInsideCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean isStoreResultsDisplayed() {
        return driver.findElements(storeCards).size() > 0;
    }

    public boolean isStoreWithAddressPresent(String address) {
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(addressInsideCard);
            for (WebElement addr : addresses) {
                if (BrowserUtils.getText(driver, addr).contains(address)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setMyStoreByAddress(String address) {
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(addressInsideCard);
            for (WebElement addr : addresses) {
                if (BrowserUtils.getText(driver, addr).contains(address)) {
                    List<WebElement> btns = card.findElements(setMyStoreButtonInsideCard);
                    if (!btns.isEmpty()) {
                        BrowserUtils.click(driver, btns.get(0));
                        return;
                    }
                }
            }
        }
    }

    public boolean isNoResultsMessageDisplayed() {
        return driver.findElements(emptyResultsMsg).size() > 0;
    }
}
