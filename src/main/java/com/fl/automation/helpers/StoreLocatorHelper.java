package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class StoreLocatorHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final List<By> LOCATION_INPUT_LOCATORS = Arrays.asList(
            By.cssSelector("input[type='search']"),
            By.cssSelector("input[name='q']"),
            By.cssSelector("input[aria-label*='Search']"),
            By.cssSelector("input[placeholder*='Search' i]")
    );

    private static final List<By> SEARCH_BUTTON_LOCATORS = Arrays.asList(
            By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]"),
            By.cssSelector("[aria-label*='Search for store' i]"),
            By.cssSelector("button[type='submit']")
    );

    private static final List<By> STORE_RESULT_LOCATORS = Arrays.asList(
            By.cssSelector("[data-qa='location']"),
            By.cssSelector(".c-location-card"),
            By.cssSelector(".location"),
            By.cssSelector("[class*='location-card']")
    );

    private static final List<By> STORE_ADDRESS_LOCATORS = Arrays.asList(
            By.cssSelector("[data-qa='address']"),
            By.cssSelector(".c-address"),
            By.cssSelector("address"),
            By.cssSelector(".address")
    );

    private static final By SET_MY_STORE_BUTTON = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public boolean isLocationTextboxVisible() {
        try {
            WebElement locationInput = findElementWithFallback(LOCATION_INPUT_LOCATORS);
            return wait.until(ExpectedConditions.visibilityOf(locationInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchButtonVisible() {
        try {
            WebElement searchButton = findElementWithFallback(SEARCH_BUTTON_LOCATORS);
            return wait.until(ExpectedConditions.visibilityOf(searchButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLocation(String location) {
        WebElement locationInput = findElementWithFallback(LOCATION_INPUT_LOCATORS);
        wait.until(ExpectedConditions.visibilityOf(locationInput));
        locationInput.clear();
        locationInput.sendKeys(location);
    }

    public void clickSearchButton() {
        WebElement searchButton = findElementWithFallback(SEARCH_BUTTON_LOCATORS);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        clickElement(searchButton);
    }

    public boolean areStoreResultsDisplayed() {
        try {
            List<WebElement> results = findElementsWithFallback(STORE_RESULT_LOCATORS);
            return results.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStoreAddressVisible(String address) {
        try {
            List<WebElement> storeCards = findElementsWithFallback(STORE_RESULT_LOCATORS);
            for (WebElement card : storeCards) {
                try {
                    for (By addressLocator : STORE_ADDRESS_LOCATORS) {
                        try {
                            WebElement addressElement = card.findElement(addressLocator);
                            if (addressElement.getText().contains(address)) {
                                return true;
                            }
                        } catch (Exception e) {
                            continue;
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSetMyStoreForAddress(String address) {
        List<WebElement> storeCards = findElementsWithFallback(STORE_RESULT_LOCATORS);
        for (WebElement card : storeCards) {
            try {
                boolean addressFound = false;
                for (By addressLocator : STORE_ADDRESS_LOCATORS) {
                    try {
                        WebElement addressElement = card.findElement(addressLocator);
                        if (addressElement.getText().contains(address)) {
                            addressFound = true;
                            break;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
                if (addressFound) {
                    WebElement setMyStoreButton = card.findElement(SET_MY_STORE_BUTTON);
                    wait.until(ExpectedConditions.elementToBeClickable(setMyStoreButton));
                    clickElement(setMyStoreButton);
                    return;
                }
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Store with address " + address + " not found");
    }

    public boolean isStoreConfirmationDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Your Store') or contains(text(),'My Store') or contains(text(),'Selected Store')]"))
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelectedStoreDisplayedInHeader(String address) {
        try {
            WebElement headerStore = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//header//*[contains(text(),'" + address + "') or contains(text(),'375 Washington')]"));
            return headerStore.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToSneakers() {
        try {
            WebElement sneakersLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href,'sneakers') or contains(text(),'Sneakers')]"));
            clickElement(sneakersLink);
        } catch (Exception e) {
            driver.get("https://www.footlocker.com/category/mens/shoes/sneakers.html");
        }
    }

    private WebElement findElementWithFallback(List<By> locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }

    private List<WebElement> findElementsWithFallback(List<By> locators) {
        for (By locator : locators) {
            try {
                List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
                if (!elements.isEmpty()) {
                    return elements;
                }
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException("Elements not found with any of the provided locators");
    }

    private void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}