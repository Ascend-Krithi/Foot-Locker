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

    public List<WebElement> getStoreResultCards() {
        return driver.findElements(storeResultCards);
    }

    public String getStoreAddress(WebElement card) {
        List<WebElement> addresses = card.findElements(storeAddressInsideCard);
        if (!addresses.isEmpty()) {
            return BrowserUtils.getText(driver, addresses.get(0));
        }
        return "";
    }

    public boolean isSetMyStoreButtonPresent(WebElement card) {
        return card.findElements(setMyStoreButtonInsideCard).size() > 0;
    }

    public void clickSetMyStoreButton(WebElement card) {
        WebElement btn = card.findElement(setMyStoreButtonInsideCard);
        BrowserUtils.click(driver, btn);
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return driver.findElements(emptyResultsMessage).size() > 0 && BrowserUtils.isDisplayed(driver, driver.findElement(emptyResultsMessage));
    }
}
