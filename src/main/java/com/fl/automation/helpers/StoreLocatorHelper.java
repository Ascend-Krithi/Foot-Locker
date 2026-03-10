package com.fl.automation.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {
    private WebDriver driver;
    private WebDriverWait wait;

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public WebElement findStoreCardByAddress(List<WebElement> storeCards, String address) {
        for (WebElement card : storeCards) {
            try {
                WebElement addressElem = card.findElement(org.openqa.selenium.By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']"));
                if (addressElem.getText().trim().contains(address)) {
                    return card;
                }
            } catch (Exception ignored) {}
        }
        return null;
    }

    public boolean isStorePresent(List<WebElement> storeCards, String address) {
        return findStoreCardByAddress(storeCards, address) != null;
    }

    public void waitForStoreResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']")));
    }
}
