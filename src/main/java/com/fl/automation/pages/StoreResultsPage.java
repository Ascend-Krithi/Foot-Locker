package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreResultsPage {
    private WebDriver driver;

    private By searchInput = By.cssSelector("input[type='search'], input[name*='search']");
    private By searchButton = By.cssSelector("button[type='submit'], button[aria-label*='search']");
    private By storeCards = By.cssSelector(".store-card, .store-listing, .store-result");
    private By storeAddress = By.cssSelector(".store-address, .address, .store-info-address");
    private By setMyStoreButton = By.xpath("//button[contains(.,'Set My Store') or contains(.,'Select My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getSearchInput() {
        return searchInput;
    }

    public By getSearchButton() {
        return searchButton;
    }

    public By getStoreCards() {
        return storeCards;
    }

    public By getStoreAddress() {
        return storeAddress;
    }

    public By getSetMyStoreButton() {
        return setMyStoreButton;
    }
}
