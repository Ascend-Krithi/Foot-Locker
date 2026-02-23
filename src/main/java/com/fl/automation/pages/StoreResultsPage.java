package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private final WebDriver driver;
    public StoreResultsPage() {
        this.driver = DriverFactory.getDriver();
    }
    private WebElement getLocationInput() {
        try {
            return driver.findElement(By.cssSelector("input[type='search']"));
        } catch (Exception e1) {
            try {
                return driver.findElement(By.cssSelector("input[name='q']"));
            } catch (Exception e2) {
                try {
                    return driver.findElement(By.cssSelector("input[aria-label*='Search']"));
                } catch (Exception e3) {
                    return driver.findElement(By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]"));
                }
            }
        }
    }
    private WebElement getSearchButton() {
        return driver.findElement(By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search')]") );
    }
    private List<WebElement> getStoreCards() {
        return driver.findElements(By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']"));
    }
    private WebElement getStoreAddressInCard(WebElement card) {
        return card.findElement(By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']"));
    }
    private WebElement getSetMyStoreButtonInCard(WebElement card) {
        return card.findElement(By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]") );
    }
    private WebElement getEmptyResultsMessage() {
        return driver.findElement(By.xpath("//*[contains(.,'There are no locations in your search area')]") );
    }
    public void enterLocation(String location) {
        BrowserUtils.type(getLocationInput(), location, driver);
    }
    public void clickSearch() {
        BrowserUtils.click(getSearchButton(), driver);
    }
    public boolean isLocationInputDisplayed() {
        return BrowserUtils.isDisplayed(getLocationInput(), driver);
    }
    public boolean isSearchButtonEnabled() {
        return getSearchButton().isEnabled();
    }
    public boolean isStoreResultDisplayed(String address) {
        for (WebElement card : getStoreCards()) {
            String addr = BrowserUtils.getText(getStoreAddressInCard(card), driver);
            if (addr != null && addr.contains(address)) {
                return true;
            }
        }
        return false;
    }
    public boolean setMyStore(String address) {
        for (WebElement card : getStoreCards()) {
            String addr = BrowserUtils.getText(getStoreAddressInCard(card), driver);
            if (addr != null && addr.contains(address)) {
                BrowserUtils.scrollIntoView(card, driver);
                BrowserUtils.click(getSetMyStoreButtonInCard(card), driver);
                return true;
            }
        }
        return false;
    }
    public boolean isNoStoresFoundMessageDisplayed() {
        try {
            return BrowserUtils.isDisplayed(getEmptyResultsMessage(), driver);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isErrorMessageDisplayed() {
        return driver.getPageSource().contains("Location field is required") || driver.getPageSource().contains("Please enter a location");
    }
    public boolean isStoreNameDisplayedGlobally(String address) {
        return driver.getPageSource().contains(address);
    }
}
