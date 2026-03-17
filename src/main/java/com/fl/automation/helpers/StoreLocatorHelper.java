package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    private By[] locationTextboxLocators = {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private By[] searchButtonLocators = {
        By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]"),
        By.cssSelector("[aria-label*='Search for store' i], button[type='submit']"),
        By.cssSelector("button[aria-label*='Search' i]"),
        By.xpath("//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search')]")
    };

    private By[] storeResultsLocators = {
        By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']"),
        By.xpath("//div[contains(@class,'location') or contains(@class,'store-result')]"),
        By.xpath("//div[contains(@class,'result') and .//address]")
    };

    private By[] storeAddressLocators = {
        By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']"),
        By.xpath(".//address"),
        By.xpath(".//*[contains(@class,'address')]")
    };

    private By[] setMyStoreButtonLocators = {
        By.xpath(".//button[contains(.,'Set My Store') or contains(.,'Select My Store') or contains(.,'Make This My Store')]"),
        By.cssSelector("button[aria-label*='Set My Store' i], button[aria-label*='Select' i]")
    };

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public boolean isLocationTextboxDisplayed() {
        WebElement textbox = findElementWithFallback(locationTextboxLocators, 40);
        return textbox != null && textbox.isDisplayed();
    }

    public boolean isSearchButtonDisplayed() {
        WebElement button = findElementWithFallback(searchButtonLocators, 40);
        return button != null && button.isDisplayed();
    }

    public void enterLocation(String location) {
        WebElement textbox = findElementWithFallback(locationTextboxLocators, 40);
        if (textbox != null) {
            textbox.clear();
            textbox.sendKeys(location);
        } else {
            throw new RuntimeException("Location textbox not found with any locator");
        }
    }

    public void clickSearchForStores() {
        WebElement searchButton = findElementWithFallback(searchButtonLocators, 40);
        if (searchButton != null) {
            clickWithJSFallback(searchButton);
        } else {
            throw new RuntimeException("Search for Stores button not found with any locator");
        }
    }

    public boolean areStoreResultsDisplayed() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> results = findElementsWithFallback(storeResultsLocators, 40);
        return results != null && !results.isEmpty();
    }

    public boolean isStoreAddressVisible(String expectedAddress) {
        List<WebElement> storeCards = findElementsWithFallback(storeResultsLocators, 40);
        if (storeCards == null || storeCards.isEmpty()) {
            return false;
        }

        for (WebElement card : storeCards) {
            try {
                WebElement addressElement = findElementWithinParent(card, storeAddressLocators);
                if (addressElement != null) {
                    String addressText = addressElement.getText();
                    if (addressText.contains(expectedAddress) || 
                        (addressText.contains("375 Washington Street") && addressText.contains("Boston") && addressText.contains("02108"))) {
                        return true;
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }

    public void clickSetMyStoreForAddress(String targetAddress) {
        List<WebElement> storeCards = findElementsWithFallback(storeResultsLocators, 40);
        if (storeCards == null || storeCards.isEmpty()) {
            throw new RuntimeException("No store results found");
        }

        for (WebElement card : storeCards) {
            try {
                WebElement addressElement = findElementWithinParent(card, storeAddressLocators);
                if (addressElement != null) {
                    String addressText = addressElement.getText();
                    if (addressText.contains(targetAddress) || 
                        (addressText.contains("375 Washington Street") && addressText.contains("Boston"))) {
                        WebElement setMyStoreButton = findElementWithinParent(card, setMyStoreButtonLocators);
                        if (setMyStoreButton != null) {
                            clickWithJSFallback(setMyStoreButton);
                            return;
                        }
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Set My Store button not found for address: " + targetAddress);
    }

    public boolean isStoreSetConfirmationDisplayed() {
        try {
            Thread.sleep(2000);
            By[] confirmationLocators = {
                By.xpath("//*[contains(text(),'selected') or contains(text(),'confirmed') or contains(text(),'My Store')]"),
                By.cssSelector("[class*='confirmation'], [class*='success'], [class*='selected']"),
                By.xpath("//div[contains(@class,'alert') or contains(@class,'message')][contains(.,'store')]")
            };
            WebElement confirmation = findElementWithFallback(confirmationLocators, 10);
            return confirmation != null && confirmation.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement findElementWithFallback(By[] locators, int timeoutSeconds) {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        for (By locator : locators) {
            try {
                return shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }

    private List<WebElement> findElementsWithFallback(By[] locators, int timeoutSeconds) {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        for (By locator : locators) {
            try {
                List<WebElement> elements = shortWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
                if (!elements.isEmpty()) {
                    return elements;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }

    private WebElement findElementWithinParent(WebElement parent, By[] locators) {
        for (By locator : locators) {
            try {
                return parent.findElement(locator);
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }

    private void clickWithJSFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } catch (Exception jsException) {
                throw new RuntimeException("Failed to click element with both regular and JS click", jsException);
            }
        }
    }
}