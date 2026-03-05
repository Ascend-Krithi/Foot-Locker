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

    public boolean areResultsDisplayed() {
        try {
            List<WebElement> cards = driver.findElements(storeResultCards);
            return cards != null && cards.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStorePresent(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            WebElement addr = null;
            try {
                addr = card.findElement(storeAddressInsideCard);
            } catch (Exception e) {
                continue;
            }
            if (addr != null && BrowserUtils.getText(driver, addr).contains(address)) {
                return true;
            }
        }
        return false;
    }

    public WebElement getStoreCardByAddress(String address) {
        List<WebElement> cards = driver.findElements(storeResultCards);
        for (WebElement card : cards) {
            WebElement addr = null;
            try {
                addr = card.findElement(storeAddressInsideCard);
            } catch (Exception e) {
                continue;
            }
            if (addr != null && BrowserUtils.getText(driver, addr).contains(address)) {
                return card;
            }
        }
        return null;
    }

    public void clickSetMyStoreOnCard(WebElement card) {
        WebElement btn = card.findElement(setMyStoreButtonInsideCard);
        BrowserUtils.click(driver, btn);
    }

    public boolean isConfirmationIndicatorPresent(WebElement card) {
        try {
            WebElement indicator = card.findElement(By.xpath(".//*[contains(@class,'confirmation') or contains(.,'My Store') or contains(.,'Selected')]"));
            return BrowserUtils.isDisplayed(driver, indicator);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStoreInHeader(String address) {
        try {
            WebElement header = driver.findElement(By.cssSelector("header"));
            return BrowserUtils.getText(driver, header).contains(address);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmptyResultsMessagePresent() {
        try {
            return BrowserUtils.isDisplayed(driver, driver.findElement(emptyResultsMessage));
        } catch (Exception e) {
            return false;
        }
    }
}
