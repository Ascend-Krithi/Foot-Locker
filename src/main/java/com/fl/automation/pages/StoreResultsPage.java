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
    private By emptyResultsMessage = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean areStoreResultsDisplayed() {
        List<WebElement> cards = driver.findElements(storeResultCards);
        return cards != null && cards.size() > 0;
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, driver.findElement(emptyResultsMessage));
    }

    public List<WebElement> getStoreCards() {
        return driver.findElements(storeResultCards);
    }

    public String getStoreAddress(WebElement card) {
        return card.findElement(storeAddressInsideCard).getText();
    }
}
