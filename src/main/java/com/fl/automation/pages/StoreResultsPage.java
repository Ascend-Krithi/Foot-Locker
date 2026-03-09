package com.fl.automation.pages;

import com.fl.automation.core.BrowserUtils;
import com.fl.automation.core.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class StoreResultsPage {
    private WebDriver driver;
    private By searchInputs[] = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };
    private By storeCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private By address = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private By setMyStoreBtn = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");
    private By searchButton = By.xpath("//button[contains(.,'Search')]");
    private By emptyResultsMsg = By.xpath("//*[contains(.,'There are no locations in your search area')]");

    public StoreResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchInputDisplayed() {
        for (By by : searchInputs) {
            List<WebElement> els = driver.findElements(by);
            if (!els.isEmpty() && BrowserUtils.isDisplayed(els.get(0))) {
                return true;
            }
        }
        return false;
    }

    public boolean isSearchButtonDisplayed() {
        List<WebElement> btns = driver.findElements(searchButton);
        for (WebElement btn : btns) {
            if (BrowserUtils.isDisplayed(btn)) return true;
        }
        return false;
    }

    public void enterLocation(String location) {
        for (By by : searchInputs) {
            List<WebElement> els = driver.findElements(by);
            if (!els.isEmpty()) {
                WebElement input = els.get(0);
                WaitUtils.waitForElementVisible(driver, input);
                BrowserUtils.type(input, location);
                return;
            }
        }
    }

    public void clickSearchButton() {
        List<WebElement> btns = driver.findElements(searchButton);
        for (WebElement btn : btns) {
            if (BrowserUtils.isDisplayed(btn)) {
                WaitUtils.waitForElementClickable(driver, btn);
                BrowserUtils.click(btn);
                return;
            }
        }
    }

    public List<WebElement> getStoreResults() {
        List<WebElement> cards = driver.findElements(storeCards);
        List<WebElement> visible = new ArrayList<>();
        for (WebElement card : cards) {
            if (BrowserUtils.isDisplayed(card)) visible.add(card);
        }
        return visible;
    }

    public String getStoreAddress(WebElement storeCard) {
        List<WebElement> addresses = storeCard.findElements(address);
        for (WebElement addr : addresses) {
            if (BrowserUtils.isDisplayed(addr)) {
                return BrowserUtils.getText(addr);
            }
        }
        return null;
    }

    public void clickSetMyStore(WebElement storeCard) {
        List<WebElement> btns = storeCard.findElements(setMyStoreBtn);
        for (WebElement btn : btns) {
            if (BrowserUtils.isDisplayed(btn)) {
                WaitUtils.waitForElementClickable(driver, btn);
                BrowserUtils.click(btn);
                return;
            }
        }
    }

    public boolean isEmptyResultsMessageDisplayed() {
        List<WebElement> msgs = driver.findElements(emptyResultsMsg);
        for (WebElement msg : msgs) {
            if (BrowserUtils.isDisplayed(msg)) return true;
        }
        return false;
    }

    public boolean isStoreAddressPresent(String addressText) {
        List<WebElement> cards = getStoreResults();
        for (WebElement card : cards) {
            String addr = getStoreAddress(card);
            if (addr != null && addr.contains(addressText)) {
                return true;
            }
        }
        return false;
    }
}
