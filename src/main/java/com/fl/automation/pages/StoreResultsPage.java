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

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getStoreCards() {
        return driver.findElements(storeResultCards);
    }

    public String getStoreAddress(WebElement card) {
        return card.findElement(storeAddressInsideCard).getText();
    }

    public boolean isSetMyStoreButtonPresent(WebElement card) {
        List<WebElement> buttons = card.findElements(setMyStoreButtonInsideCard);
        return !buttons.isEmpty();
    }

    public void clickSetMyStoreButton(WebElement card) {
        WebElement button = card.findElement(setMyStoreButtonInsideCard);
        BrowserUtils.click(driver, button);
    }
}
