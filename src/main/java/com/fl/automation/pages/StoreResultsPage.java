package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private final WebDriver driver;
    private final By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By storeAddressInsideCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private final By setMyStoreBtnInsideCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private final By selectMyStoreLink = By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");
    private final By selectMyStoreBtn = By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public int getStoreResultsCount() {
        List<WebElement> cards = driver.findElements(storeResultCards);
        return cards.size();
    }

    public String getFirstStoreAddress() {
        List<WebElement> cards = driver.findElements(storeResultCards);
        if (!cards.isEmpty()) {
            WebElement address = cards.get(0).findElement(storeAddressInsideCard);
            return address.getText();
        }
        return null;
    }

    public void setFirstStoreAsMyStore() {
        List<WebElement> cards = driver.findElements(storeResultCards);
        if (!cards.isEmpty()) {
            WebElement card = cards.get(0);
            if (card.findElements(setMyStoreBtnInsideCard).size() > 0) {
                card.findElement(setMyStoreBtnInsideCard).click();
            }
        }
    }

    public boolean isSelectMyStoreDisplayed() {
        return BrowserUtils.isDisplayed(driver, selectMyStoreLink) || BrowserUtils.isDisplayed(driver, selectMyStoreBtn);
    }
}
