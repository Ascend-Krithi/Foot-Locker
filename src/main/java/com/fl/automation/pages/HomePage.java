package com.fl.automation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class HomePage {
    private final WebDriver driver;

    // Locators as per strict fallback order
    private final List<By> findStoreHeaderLocators = Arrays.asList(
            By.linkText("Find a Store"),
            By.cssSelector("header a[href*='stores.footlocker.com']"),
            By.xpath("//header//a[contains(.,'Find a Store') or contains(.,'Store Locator')]")
    );
    private final List<By> selectMyStoreLocators = Arrays.asList(
            By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]") ,
            By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    );
    private final List<By> searchInputLocators = Arrays.asList(
            By.cssSelector("input[type='search']"),
            By.cssSelector("input[name='q']"),
            By.cssSelector("input[aria-label*='Search']"),
            By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    );
    private final List<By> storeResultCardLocators = Arrays.asList(
            By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']")
    );
    private final List<By> storeAddressLocators = Arrays.asList(
            By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']")
    );
    private final List<By> setMyStoreButtonLocators = Arrays.asList(
            By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]")
    );
    private final List<By> emptyResultsMessageLocators = Arrays.asList(
            By.xpath("//*[contains(.,'There are no locations in your search area')]")
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean waitForFindStoreHeader(WebDriverWait wait) {
        for (By locator : findStoreHeaderLocators) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                return true;
            } catch (TimeoutException ignored) {}
        }
        return false;
    }

    public boolean clickSelectMyStore(WebDriverWait wait) {
        for (By locator : selectMyStoreLocators) {
            try {
                WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
                try {
                    el.click();
                } catch (ElementClickInterceptedException e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
                }
                return true;
            } catch (TimeoutException ignored) {}
        }
        return false;
    }

    public boolean enterLocationAndSearch(WebDriverWait wait, String location) {
        for (By locator : searchInputLocators) {
            try {
                WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                input.clear();
                input.sendKeys(location);
                input.sendKeys(Keys.ENTER);
                return true;
            } catch (TimeoutException ignored) {}
        }
        return false;
    }

    public boolean isStoreAddressPresent(WebDriverWait wait, String address) {
        for (By cardLocator : storeResultCardLocators) {
            try {
                List<WebElement> cards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cardLocator));
                for (WebElement card : cards) {
                    for (By addrLocator : storeAddressLocators) {
                        try {
                            WebElement addrEl = card.findElement(addrLocator);
                            if (addrEl.getText().replaceAll("\\s+", " ").trim().contains(address)) {
                                return true;
                            }
                        } catch (NoSuchElementException ignored) {}
                    }
                }
            } catch (TimeoutException ignored) {}
        }
        return false;
    }

    public boolean setMyStoreForAddress(WebDriverWait wait, String address) {
        for (By cardLocator : storeResultCardLocators) {
            try {
                List<WebElement> cards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cardLocator));
                for (WebElement card : cards) {
                    for (By addrLocator : storeAddressLocators) {
                        try {
                            WebElement addrEl = card.findElement(addrLocator);
                            if (addrEl.getText().replaceAll("\\s+", " ").trim().contains(address)) {
                                for (By btnLocator : setMyStoreButtonLocators) {
                                    try {
                                        WebElement btn = card.findElement(btnLocator);
                                        try {
                                            btn.click();
                                        } catch (ElementClickInterceptedException e) {
                                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                                        }
                                        return true;
                                    } catch (NoSuchElementException ignored) {}
                                }
                            }
                        } catch (NoSuchElementException ignored) {}
                    }
                }
            } catch (TimeoutException ignored) {}
        }
        return false;
    }

    public boolean isConfirmationIndicatorVisible(WebDriverWait wait) {
        // Confirmation indicator is typically a visual cue, e.g., a checkmark or label
        // For demo, check for a class or aria-label containing 'confirmed' or 'my-store'
        try {
            return driver.findElements(By.cssSelector("[class*='confirmed'], [class*='my-store'], [aria-label*='confirmed'], [aria-label*='My Store']")).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmptyResultsMessageVisible(WebDriverWait wait) {
        for (By locator : emptyResultsMessageLocators) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                return true;
            } catch (TimeoutException ignored) {}
        }
        return false;
    }
}
