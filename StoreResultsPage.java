package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import com.fl.automation.core.BrowserUtils;

public class StoreResultsPage {
    private WebDriver driver;
    private static final By STORE_RESULT_CARDS = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private static final By STORE_ADDRESS_INSIDE_CARD = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private static final By SET_MY_STORE_BUTTON_INSIDE_CARD = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private static final By EMPTY_RESULTS_MESSAGE = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isStoreResultDisplayed() {
        return BrowserUtils.isDisplayed(driver, STORE_RESULT_CARDS);
    }

    public boolean isStoreWithAddressDisplayed(String address) {
        List<WebElement> cards = driver.findElements(STORE_RESULT_CARDS);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(STORE_ADDRESS_INSIDE_CARD);
            for (WebElement addr : addresses) {
                if (addr.getText().contains(address)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean setMyStoreByAddress(String address) {
        List<WebElement> cards = driver.findElements(STORE_RESULT_CARDS);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(STORE_ADDRESS_INSIDE_CARD);
            for (WebElement addr : addresses) {
                if (addr.getText().contains(address)) {
                    List<WebElement> btns = card.findElements(SET_MY_STORE_BUTTON_INSIDE_CARD);
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
        List<WebElement> cards = driver.findElements(STORE_RESULT_CARDS);
        for (WebElement card : cards) {
            if (card.getText().toLowerCase().contains("my store") || card.getText().toLowerCase().contains("preferred")) {
                return true;
            }
        }
        return false;
    }

    public boolean isNoStoresFoundMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, EMPTY_RESULTS_MESSAGE);
    }
}
