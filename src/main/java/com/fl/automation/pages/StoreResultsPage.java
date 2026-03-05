package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private final By searchInput = By.cssSelector("input[type='search']");
    private final By searchInputName = By.cssSelector("input[name='q']");
    private final By searchInputAria = By.cssSelector("input[aria-label*='Search']");
    private final By searchInputPlaceholder = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private final By searchButton = By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search')]");
    private final By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private final By addressInCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private final By setMyStoreButtonInCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private final By emptyResultsMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        return BrowserUtils.isDisplayed(driver, searchInput) ||
               BrowserUtils.isDisplayed(driver, searchInputName) ||
               BrowserUtils.isDisplayed(driver, searchInputAria) ||
               BrowserUtils.isDisplayed(driver, searchInputPlaceholder);
    }

    public void enterLocation(String location) {
        if (BrowserUtils.isDisplayed(driver, searchInput)) {
            BrowserUtils.type(driver, searchInput, location);
        } else if (BrowserUtils.isDisplayed(driver, searchInputName)) {
            BrowserUtils.type(driver, searchInputName, location);
        } else if (BrowserUtils.isDisplayed(driver, searchInputAria)) {
            BrowserUtils.type(driver, searchInputAria, location);
        } else {
            BrowserUtils.type(driver, searchInputPlaceholder, location);
        }
    }

    public boolean isSearchButtonDisplayed() {
        return BrowserUtils.isDisplayed(driver, searchButton);
    }

    public void clickSearchButton() {
        BrowserUtils.click(driver, searchButton);
    }

    public boolean isStoreResultsDisplayed() {
        List<WebElement> cards = driver.findElements(storeCards);
        return cards != null && !cards.isEmpty();
    }

    public boolean isStoreWithAddressPresent(String address) {
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(addressInCard);
            for (WebElement addr : addresses) {
                if (addr.getText().replaceAll("\n", " ").contains(address)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setMyStoreForAddress(String address) {
        List<WebElement> cards = driver.findElements(storeCards);
        for (WebElement card : cards) {
            List<WebElement> addresses = card.findElements(addressInCard);
            for (WebElement addr : addresses) {
                if (addr.getText().replaceAll("\n", " ").contains(address)) {
                    List<WebElement> setBtns = card.findElements(setMyStoreButtonInCard);
                    if (!setBtns.isEmpty()) {
                        setBtns.get(0).click();
                        return;
                    }
                }
            }
        }
    }

    public boolean isConfirmationIndicatorPresent() {
        // Check for confirmation indicator, e.g., a checkmark or confirmation message
        By confirmation = By.xpath("//*[contains(.,'Your store has been set') or contains(@class,'selected-store') or contains(.,'preferred store')]");
        return BrowserUtils.isDisplayed(driver, confirmation);
    }

    public boolean isSelectedStoreInHeader(String address) {
        // Check if selected store appears in header/location indicator
        By headerIndicator = By.xpath("//header//*[contains(.,'" + address + "') or contains(.,'preferred store')]");
        return BrowserUtils.isDisplayed(driver, headerIndicator);
    }

    public void navigateToAnotherPage() {
        // For test, click on logo or any other link
        By logo = By.cssSelector("a[aria-label='Foot Locker'], a.logo, a[href='/']");
        if (BrowserUtils.isDisplayed(driver, logo)) {
            BrowserUtils.click(driver, logo);
        } else {
            driver.navigate().to("https://www.footlocker.com/men");
        }
    }

    public void returnToHomePage() {
        driver.navigate().to(com.fl.automation.core.ConfigReader.get("base.url"));
    }
}
