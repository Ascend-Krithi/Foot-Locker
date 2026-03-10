package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoreLocatorHelper {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    private static final By[] SEARCH_INPUT_LOCATORS = {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private static final By STORE_RESULT_CARDS = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");
    private static final By STORE_ADDRESS = By.cssSelector("[data-qa='address'], .c-address, address, .address, [class*='address']");
    private static final By SET_MY_STORE_BUTTON = By.xpath(".//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store') or contains(.,'My Store')]");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        this.js = (JavascriptExecutor) driver;
    }

    public void enterSearchLocation(String location) {
        WebElement searchInput = findElementWithFallback(SEARCH_INPUT_LOCATORS);
        searchInput.clear();
        searchInput.sendKeys(location);
    }

    public List<WebElement> getStoreResultCards() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(STORE_RESULT_CARDS));
    }

    public WebElement findStoreByAddress(String address) {
        List<WebElement> storeCards = getStoreResultCards();
        for (WebElement card : storeCards) {
            try {
                WebElement addressElement = card.findElement(STORE_ADDRESS);
                String storeAddress = addressElement.getText();
                if (storeAddress.contains(address)) {
                    return card;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }

    public void clickSetMyStoreForCard(WebElement storeCard) {
        WebElement setMyStoreButton = storeCard.findElement(SET_MY_STORE_BUTTON);
        clickElementWithFallback(setMyStoreButton);
    }

    private WebElement findElementWithFallback(By[] locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (TimeoutException e) {
                continue;
            }
        }
        throw new RuntimeException("Element not found with any of the provided locators");
    }

    private void clickElementWithFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }
    }
}