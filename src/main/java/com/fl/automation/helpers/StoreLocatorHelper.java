package com.fl.automation.helpers;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Slf4j
public class StoreLocatorHelper {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public WebElement findStoreHeader() {
        By[] locators = new By[] {
                By.linkText("Find a Store"),
                By.cssSelector("header a[href*='stores.footlocker.com']"),
                By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
        };
        return findElementWithFallback(locators);
    }

    public WebElement selectMyStoreLink() {
        By[] locators = new By[] {
                By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')"]),
                By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')"])
        };
        return findElementWithFallback(locators);
    }

    public WebElement searchInput() {
        By[] locators = new By[] {
                By.cssSelector("input[type='search']"),
                By.cssSelector("input[name='q']"),
                By.cssSelector("input[aria-label*='Search']"),
                By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
        };
        return findElementWithFallback(locators);
    }

    public WebElement searchButton() {
        // Try to find a button next to the search input
        WebElement input = searchInput();
        WebElement parent = input.findElement(By.xpath(".."));
        List<WebElement> buttons = parent.findElements(By.tagName("button"));
        if (!buttons.isEmpty()) {
            return buttons.get(0);
        }
        // Fallback: find button in form
        WebElement form = input.findElement(By.xpath("ancestor::form"));
        return form.findElement(By.tagName("button"));
    }

    public List<WebElement> storeResultCards() {
        By[] locators = new By[] {
                By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']")
        };
        return findElementsWithFallback(locators);
    }

    public WebElement storeAddressInCard(WebElement card) {
        By[] locators = new By[] {
                By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']")
        };
        for (By locator : locators) {
            List<WebElement> addresses = card.findElements(locator);
            if (!addresses.isEmpty()) {
                return addresses.get(0);
            }
        }
        throw new NoSuchElementException("Store address not found in card");
    }

    public WebElement setMyStoreButtonInCard(WebElement card) {
        By[] locators = new By[] {
                By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]")
        };
        for (By locator : locators) {
            List<WebElement> btns = card.findElements(locator);
            if (!btns.isEmpty()) {
                return btns.get(0);
            }
        }
        throw new NoSuchElementException("Set My Store button not found in card");
    }

    public WebElement emptyResultsMessage() {
        By[] locators = new By[] {
                By.xpath("//*[contains(.,'There are no locations in your search area')]")
        };
        return findElementWithFallback(locators);
    }

    private WebElement findElementWithFallback(By[] locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (TimeoutException ignored) {}
        }
        throw new NoSuchElementException("Element not found with any locator");
    }

    private List<WebElement> findElementsWithFallback(By[] locators) {
        for (By locator : locators) {
            try {
                List<WebElement> elements = wait.until(driver -> driver.findElements(locator));
                if (!elements.isEmpty()) {
                    return elements;
                }
            } catch (TimeoutException ignored) {}
        }
        throw new NoSuchElementException("Elements not found with any locator");
    }
}
