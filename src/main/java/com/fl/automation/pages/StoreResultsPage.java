package com.fl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import com.fl.automation.core.BrowserUtils;

public class StoreResultsPage {
    private WebDriver driver;
    private By searchInput1 = By.cssSelector("input[type='search']");
    private By searchInput2 = By.cssSelector("input[name='q']");
    private By searchInput3 = By.cssSelector("input[aria-label*='Search']");
    private By searchInput4 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
    private By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By addressInCard = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreBtnInCard = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By emptyResultsMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        return driver.findElements(searchInput1).size() > 0 ||
               driver.findElements(searchInput2).size() > 0 ||
               driver.findElements(searchInput3).size() > 0 ||
               driver.findElements(searchInput4).size() > 0;
    }

    public boolean isSearchButtonDisplayed() {
        // Assume search button is next to search input
        List<WebElement> inputs = driver.findElements(searchInput1);
        if (inputs.isEmpty()) inputs = driver.findElements(searchInput2);
        if (inputs.isEmpty()) inputs = driver.findElements(searchInput3);
        if (inputs.isEmpty()) inputs = driver.findElements(searchInput4);
        if (!inputs.isEmpty()) {
            WebElement input = inputs.get(0);
            List<WebElement> btns = input.findElements(By.xpath("following-sibling::button|following-sibling::input[@type='submit']"));
            return !btns.isEmpty();
        }
        return false;
    }

    public void searchForStore(String query) {
        WebElement input = null;
        if (driver.findElements(searchInput1).size() > 0) {
            input = driver.findElement(searchInput1);
        } else if (driver.findElements(searchInput2).size() > 0) {
            input = driver.findElement(searchInput2);
        } else if (driver.findElements(searchInput3).size() > 0) {
            input = driver.findElement(searchInput3);
        } else if (driver.findElements(searchInput4).size() > 0) {
            input = driver.findElement(searchInput4);
        }
        if (input != null) {
            BrowserUtils.type(driver, input, query);
            input.submit();
        } else {
            throw new RuntimeException("Search input not found");
        }
    }

    public boolean hasStoreResults() {
        return driver.findElements(storeCards).size() > 0;
    }

    public String getFirstStoreAddress() {
        List<WebElement> cards = driver.findElements(storeCards);
        if (!cards.isEmpty()) {
            WebElement card = cards.get(0);
            List<WebElement> addresses = card.findElements(addressInCard);
            if (!addresses.isEmpty()) {
                return BrowserUtils.getText(driver, addresses.get(0));
            }
        }
        return null;
    }

    public void setFirstStoreAsMyStore() {
        List<WebElement> cards = driver.findElements(storeCards);
        if (!cards.isEmpty()) {
            WebElement card = cards.get(0);
            List<WebElement> btns = card.findElements(setMyStoreBtnInCard);
            if (!btns.isEmpty()) {
                BrowserUtils.click(driver, btns.get(0));
            } else {
                throw new RuntimeException("Set My Store button not found in card");
            }
        } else {
            throw new RuntimeException("No store cards found");
        }
    }

    public boolean isEmptyResultsMessageDisplayed() {
        return driver.findElements(emptyResultsMsg).size() > 0;
    }
}