package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import com.fl.automation.core.BrowserUtils;

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
        try {
            List<WebElement> cards = driver.findElements(storeResultCards);
            return cards != null && cards.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoResultsMessageDisplayed() {
        try {
            return BrowserUtils.isDisplayed(driver, driver.findElement(emptyResultsMessage));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStoreAddressPresent(String address) {
        try {
            List<WebElement> cards = driver.findElements(storeResultCards);
            for (WebElement card : cards) {
                WebElement addrElem = card.findElement(storeAddressInsideCard);
                String addrText = BrowserUtils.getText(driver, addrElem);
                if (addrText != null && addrText.contains(address)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void setMyStore(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            WebElement addrElem = card.findElement(storeAddressInsideCard);
            String addrText = BrowserUtils.getText(driver, addrElem);
            if (addrText != null && addrText.contains(address)) {
                WebElement setBtn = card.findElement(setMyStoreButtonInsideCard);
                BrowserUtils.scrollIntoView(driver, setBtn);
                BrowserUtils.click(driver, setBtn);
                break;
            }
        }
    }

    public boolean isStoreSetConfirmationDisplayed(String address) {
        try {
            List<WebElement> cards = driver.findElements(storeResultCards);
            for (WebElement card : cards) {
                WebElement addrElem = card.findElement(storeAddressInsideCard);
                String addrText = BrowserUtils.getText(driver, addrElem);
                if (addrText != null && addrText.contains(address)) {
                    WebElement setBtn = card.findElement(setMyStoreButtonInsideCard);
                    String btnText = BrowserUtils.getText(driver, setBtn);
                    if (btnText != null && btnText.contains("My Store")) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
