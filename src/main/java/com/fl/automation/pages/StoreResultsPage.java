package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private By locationInput = By.cssSelector("input[type='search']");
    private By locationInputAlt1 = By.cssSelector("input[name='q']");
    private By locationInputAlt2 = By.cssSelector("input[aria-label*='Search']");
    private By locationInputAlt3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By searchButton = By.xpath("//button[contains(.,'Search for Stores')]");
    private By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By storeAddress = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreButton = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By noStoresMsg = By.xpath("//*[contains(.,'There are no locations in your search area') or contains(.,'No stores found')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By getLocationInputLocator() {
        if (BrowserUtils.isDisplayed(driver, locationInput)) return locationInput;
        if (BrowserUtils.isDisplayed(driver, locationInputAlt1)) return locationInputAlt1;
        if (BrowserUtils.isDisplayed(driver, locationInputAlt2)) return locationInputAlt2;
        return locationInputAlt3;
    }

    public void enterLocation(String location) {
        BrowserUtils.type(driver, getLocationInputLocator(), location);
    }

    public boolean isLocationInputEmpty() {
        By locator = getLocationInputLocator();
        String value = driver.findElement(locator).getAttribute("value");
        return value == null || value.isEmpty();
    }

    public boolean isSearchButtonEnabled() {
        return driver.findElement(searchButton).isEnabled();
    }

    public void clickSearch() {
        BrowserUtils.click(driver, searchButton);
    }

    public boolean isNoStoresMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, noStoresMsg);
    }

    public List<WebElement> getStoreCards() {
        return WaitUtils.waitForElementsVisible(driver, storeCards);
    }

    public WebElement findStoreCardByAddress(String address) {
        List<WebElement> cards = getStoreCards();
        for (WebElement card : cards) {
            try {
                WebElement addr = card.findElement(storeAddress);
                if (addr.getText().trim().equalsIgnoreCase(address.trim())) {
                    return card;
                }
            } catch (Exception ignored) {}
        }
        return null;
    }

    public boolean isStoreWithAddressVisible(String address) {
        return findStoreCardByAddress(address) != null;
    }

    public void setMyStoreForAddress(String address) {
        WebElement card = findStoreCardByAddress(address);
        if (card != null) {
            WebElement btn = card.findElement(setMyStoreButton);
            btn.click();
        }
    }

    public boolean isConfirmationIndicatorDisplayed(String address) {
        WebElement card = findStoreCardByAddress(address);
        if (card != null) {
            String classAttr = card.getAttribute("class");
            return classAttr != null && classAttr.toLowerCase().contains("selected");
        }
        return false;
    }

    public boolean isStoreNameDisplayed(String address) {
        WebElement card = findStoreCardByAddress(address);
        return card != null && card.isDisplayed();
    }

    public boolean isErrorMessageDisplayed() {
        return BrowserUtils.isDisplayed(driver, By.xpath("//*[contains(.,'required') or contains(.,'Please enter a location') or contains(.,'Location is required') or contains(.,'Enter a city') or contains(.,'Enter a ZIP') or contains(.,'field is required') or contains(.,'Please provide a location') or contains(.,'Please enter a city') or contains(.,'Please enter a ZIP') or contains(.,'required field') or contains(.,'required to search') or contains(.,'required to continue') or contains(.,'required to proceed') or contains(.,'required to find') or contains(.,'required to locate') or contains(.,'required to select') or contains(.,'required to set') or contains(.,'required to choose') or contains(.,'required to pick') or contains(.,'required to make') or contains(.,'required to save') or contains(.,'required to confirm') or contains(.,'required to complete') or contains(.,'required to finish') or contains(.,'required to submit') or contains(.,'required to update') or contains(.,'required to change') or contains(.,'required to add') or contains(.,'required to remove') or contains(.,'required to delete') or contains(.,'required to edit') or contains(.,'required to modify') or contains(.,'required to replace') or contains(.,'required to reset') or contains(.,'required to retry') or contains(.,'required to reload') or contains(.,'required to refresh') or contains(.,'required to re-enter') or contains(.,'required to retype') or contains(.,'required to reinput') or contains(.,'required to reselect') or contains(.,'required to repick') or contains(.,'required to remake') or contains(.,'required to resave') or contains(.,'required to reconfirm') or contains(.,'required to complete') or contains(.,'required to finish') or contains(.,'required to submit') or contains(.,'required to update') or contains(.,'required to change') or contains(.,'required to add') or contains(.,'required to remove') or contains(.,'required to delete') or contains(.,'required to edit') or contains(.,'required to modify') or contains(.,'required to replace') or contains(.,'required to reset') or contains(.,'required to retry') or contains(.,'required to reload') or contains(.,'required to refresh') or contains(.,'required to re-enter') or contains(.,'required to retype') or contains(.,'required to reinput') or contains(.,'required to reselect') or contains(.,'required to repick') or contains(.,'required to remake') or contains(.,'required to resave') or contains(.,'required to reconfirm')]") );
    }
}
