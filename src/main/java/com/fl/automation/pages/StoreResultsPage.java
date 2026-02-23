package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fl.automation.core.BrowserUtils;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private By[] storeResultCardLocators = new By[] {
        By.cssSelector("[data-qa='location']"),
        By.cssSelector(".c-location-card"),
        By.cssSelector(".location"),
        By.cssSelector("[class*='location-card']")
    };
    private By[] storeAddressLocators = new By[] {
        By.cssSelector("[data-qa='address']"),
        By.cssSelector(".c-address"),
        By.cssSelector("address"),
        By.cssSelector(".address"),
        By.cssSelector("[class*='address']")
    };
    private By setMyStoreButtonInCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isStoreResultPresent(String address) {
        for (By cardLocator : storeResultCardLocators) {
            List<WebElement> cards = driver.findElements(cardLocator);
            for (WebElement card : cards) {
                for (By addressLocator : storeAddressLocators) {
                    try {
                        WebElement addrElem = card.findElement(addressLocator);
                        if (BrowserUtils.getText(driver, addrElem).trim().contains(address)) {
                            return true;
                        }
                    } catch (Exception ignored) {}
                }
            }
        }
        return false;
    }

    public boolean isAnyStoreResultPresent() {
        for (By cardLocator : storeResultCardLocators) {
            List<WebElement> cards = driver.findElements(cardLocator);
            if (!cards.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean isNoResultsMessageDisplayed() {
        try {
            return driver.findElement(emptyResultsMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void setMyStore(String address) {
        for (By cardLocator : storeResultCardLocators) {
            List<WebElement> cards = driver.findElements(cardLocator);
            for (WebElement card : cards) {
                for (By addressLocator : storeAddressLocators) {
                    try {
                        WebElement addrElem = card.findElement(addressLocator);
                        if (BrowserUtils.getText(driver, addrElem).trim().contains(address)) {
                            WebElement btn = card.findElement(setMyStoreButtonInCard);
                            BrowserUtils.click(driver, btn);
                            return;
                        }
                    } catch (Exception ignored) {}
                }
            }
        }
        throw new RuntimeException("Set My Store button for address not found");
    }

    public boolean isConfirmationIndicatorDisplayed() {
        // Placeholder: implement logic to check for confirmation indicator (e.g., checkmark, toast, etc.)
        // For demo, return true if any store card has a class indicating selection
        for (By cardLocator : storeResultCardLocators) {
            List<WebElement> cards = driver.findElements(cardLocator);
            for (WebElement card : cards) {
                String classAttr = card.getAttribute("class");
                if (classAttr != null && classAttr.toLowerCase().contains("selected")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPreferredStorePersisted(String address) {
        // Placeholder: implement logic to verify preferred store is shown in header or elsewhere
        // For demo, check if store with address is present and has 'selected' class
        for (By cardLocator : storeResultCardLocators) {
            List<WebElement> cards = driver.findElements(cardLocator);
            for (WebElement card : cards) {
                String classAttr = card.getAttribute("class");
                for (By addressLocator : storeAddressLocators) {
                    try {
                        WebElement addrElem = card.findElement(addressLocator);
                        if (BrowserUtils.getText(driver, addrElem).trim().contains(address) &&
                            classAttr != null && classAttr.toLowerCase().contains("selected")) {
                            return true;
                        }
                    } catch (Exception ignored) {}
                }
            }
        }
        return false;
    }
}
