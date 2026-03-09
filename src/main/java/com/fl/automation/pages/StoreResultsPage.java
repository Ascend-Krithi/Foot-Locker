package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private final WebDriver driver;
    private final By storeCards = By.cssSelector("[data-qa='location']");
    private final By storeAddress = By.cssSelector("[data-qa='address']");
    private final By setMyStoreButton = By.xpath(".//button[contains(.,'Set My Store')]");
    private final By emptyResults = By.xpath("//*[contains(.,'There are no locations')]");

    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }

    public int getStoreCardCount() {
        List<WebElement> cards = driver.findElements(storeCards);
        return cards.size();
    }

    public String getFirstStoreAddress() {
        List<WebElement> cards = driver.findElements(storeCards);
        if (cards.isEmpty()) return null;
        WebElement address = cards.get(0).findElement(storeAddress);
        return address.getText();
    }

    public void clickSetMyStoreOnFirstCard() {
        List<WebElement> cards = driver.findElements(storeCards);
        if (!cards.isEmpty()) {
            WebElement btn = cards.get(0).findElement(setMyStoreButton);
            btn.click();
        }
    }

    public boolean isEmptyResultsDisplayed() {
        return BrowserUtils.isDisplayed(driver, emptyResults);
    }
}
