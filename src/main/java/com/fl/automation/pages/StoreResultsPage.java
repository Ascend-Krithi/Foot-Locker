package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.fl.automation.core.BrowserUtils;
import java.util.List;
import org.openqa.selenium.WebElement;

public class StoreResultsPage {
    private WebDriver driver;
    private By searchInput = By.cssSelector("input[type='search']");
    private By searchButton = By.xpath("//button[contains(.,'Search')]");
    private By storeCards = By.cssSelector(".c-location-card");
    private By storeAddress = By.cssSelector(".c-address");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Set My Store')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterLocation(String location) {
        BrowserUtils.type(driver, searchInput, location);
    }

    public void clickSearch() {
        BrowserUtils.click(driver, searchButton);
    }

    public List<WebElement> getStoreCards() {
        return driver.findElements(storeCards);
    }

    public String getStoreAddress(WebElement card) {
        return card.findElement(storeAddress).getText();
    }

    public void clickSetMyStore(WebElement card) {
        card.findElement(setMyStoreButton).click();
    }
}
