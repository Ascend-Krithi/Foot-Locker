package com.fl.automation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators with strict fallback order
    private static final By[] FIND_STORE_HEADER_LOCATORS = new By[] {
        By.linkText("Find a Store"),
        By.cssSelector("header a[href*='stores.footlocker.com']"),
        By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    };
    private static final By[] SELECT_MY_STORE_LOCATORS = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')"]),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };
    private static final By[] SEARCH_INPUT_LOCATORS = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };
    private static final By[] STORE_RESULT_CARDS_LOCATORS = new By[] {
        By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']")
    };
    private static final By[] STORE_ADDRESS_INSIDE_CARD_LOCATORS = new By[] {
        By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']")
    };
    private static final By[] SET_MY_STORE_BUTTON_INSIDE_CARD_LOCATORS = new By[] {
        By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]")
    };
    private static final By[] EMPTY_RESULTS_MESSAGE_LOCATORS = new By[] {
        By.xpath("//*[contains(.,'There are no locations in your search area')]")
    };

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public WebElement findElementWithFallback(By[] locators, WebDriverWait waitToUse) {
        for (By locator : locators) {
            try {
                WebElement elem = waitToUse.until(ExpectedConditions.visibilityOfElementLocated(locator));
                if (elem != null && elem.isDisplayed()) {
                    return elem;
                }
            } catch (Exception ignored) {}
        }
        return null;
    }

    public void clickWithJsFallback(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void clickFindStore() {
        WebElement findStore = findElementWithFallback(FIND_STORE_HEADER_LOCATORS, wait);
        if (findStore == null) throw new RuntimeException("Find a Store link not found");
        clickWithJsFallback(findStore);
    }

    public void clickSelectMyStore() {
        WebElement selectMyStore = findElementWithFallback(SELECT_MY_STORE_LOCATORS, wait);
        if (selectMyStore == null) throw new RuntimeException("Select My Store link/button not found");
        clickWithJsFallback(selectMyStore);
    }

    public void enterLocation(String location) {
        WebElement searchInput = findElementWithFallback(SEARCH_INPUT_LOCATORS, wait);
        if (searchInput == null) throw new RuntimeException("Search input not found");
        searchInput.clear();
        searchInput.sendKeys(location);
    }

    public void clickSearchButton() {
        // Try to find a button near the search input
        WebElement searchInput = findElementWithFallback(SEARCH_INPUT_LOCATORS, wait);
        if (searchInput == null) throw new RuntimeException("Search input not found");
        WebElement parent = searchInput.findElement(By.xpath("./ancestor::form | ./parent::*"));
        WebElement searchBtn = null;
        try {
            searchBtn = parent.findElement(By.cssSelector("button[type='submit'], button, input[type='submit']"));
        } catch (Exception ignored) {}
        if (searchBtn == null) {
            // fallback: try to find a visible button nearby
            List<WebElement> btns = driver.findElements(By.cssSelector("button, input[type='submit']"));
            for (WebElement btn : btns) {
                if (btn.isDisplayed() && (btn.getText().toLowerCase().contains("search") || btn.getAttribute("aria-label") != null && btn.getAttribute("aria-label").toLowerCase().contains("search"))) {
                    searchBtn = btn;
                    break;
                }
            }
        }
        if (searchBtn == null) throw new RuntimeException("Search button not found");
        clickWithJsFallback(searchBtn);
    }

    public boolean isSelectMyStoreVisible() {
        return findElementWithFallback(SELECT_MY_STORE_LOCATORS, wait) != null;
    }

    public boolean isSearchInputVisible() {
        return findElementWithFallback(SEARCH_INPUT_LOCATORS, wait) != null;
    }

    public boolean isSearchButtonVisible() {
        WebElement searchInput = findElementWithFallback(SEARCH_INPUT_LOCATORS, wait);
        if (searchInput == null) return false;
        WebElement parent = searchInput.findElement(By.xpath("./ancestor::form | ./parent::*"));
        try {
            WebElement searchBtn = parent.findElement(By.cssSelector("button[type='submit'], button, input[type='submit']"));
            return searchBtn.isDisplayed();
        } catch (Exception ignored) {}
        return false;
    }

    public boolean areStoreResultsDisplayed() {
        List<WebElement> cards = getStoreResultCards();
        return cards != null && !cards.isEmpty();
    }

    public List<WebElement> getStoreResultCards() {
        for (By locator : STORE_RESULT_CARDS_LOCATORS) {
            try {
                List<WebElement> cards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
                if (cards != null && !cards.isEmpty()) {
                    return cards;
                }
            } catch (Exception ignored) {}
        }
        return null;
    }

    public WebElement findStoreByAddress(String address) {
        List<WebElement> cards = getStoreResultCards();
        if (cards == null) return null;
        for (WebElement card : cards) {
            for (By addrLocator : STORE_ADDRESS_INSIDE_CARD_LOCATORS) {
                try {
                    WebElement addrElem = card.findElement(addrLocator);
                    if (addrElem.getText().trim().contains(address)) {
                        return card;
                    }
                } catch (Exception ignored) {}
            }
        }
        return null;
    }

    public void clickSetMyStoreForCard(WebElement storeCard) {
        for (By btnLocator : SET_MY_STORE_BUTTON_INSIDE_CARD_LOCATORS) {
            try {
                WebElement btn = storeCard.findElement(btnLocator);
                clickWithJsFallback(btn);
                return;
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Set My Store button not found in card");
    }

    public boolean isStoreSetInHeader(String storeName) {
        try {
            WebElement header = driver.findElement(By.cssSelector("header"));
            return header.getText().contains(storeName);
        } catch (Exception ignored) {}
        return false;
    }
}
