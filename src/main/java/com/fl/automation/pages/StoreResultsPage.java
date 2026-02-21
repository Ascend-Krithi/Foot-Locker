package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private final By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By addressInCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private final By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private final By emptyResultsMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getStoreResultsCount() {
        List<WebElement> stores = driver.findElements(storeCards);
        return stores.size();
    }

    public String getStoreAddress(int index) {
        List<WebElement> stores = driver.findElements(storeCards);
        if (stores.size() > index) {
            WebElement address = stores.get(index).findElement(addressInCard);
            return address.getText();
        }
        return null;
    }

    public void selectStore(int index) {
        List<WebElement> stores = driver.findElements(storeCards);
        if (stores.size() > index) {
            WebElement btn = stores.get(index).findElement(setMyStoreButton);
            btn.click();
        }
    }

    public boolean isEmptyResultsDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResultsMsg);
    }
}
