package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreLocatorHelper {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By[] selectMyStoreLocators = new By[] {
        By.xpath("//a[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]"),
        By.xpath("//button[contains(.,'Select My Store') or contains(.,'Set My Store') or contains(.,'Make This My Store') or contains(.,'Set as My Store')]")
    };

    private final By[] searchInputLocators = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private final By[] searchButtonLocators = new By[] {
        By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search Stores') or contains(.,'Find Stores')]")
    };

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void openStoreLocator() {
        WebElement target = null;
        for (By locator : selectMyStoreLocators) {
            try {
                target = wait.until(ExpectedConditions.elementToBeClickable(locator));
                break;
            } catch (TimeoutException ignored) {
            }
        }

        if (target == null) {
            throw new TimeoutException("Could not locate 'Select My Store' button using provided locators.");
        }

        try {
            target.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", target);
        }
    }

    public WebElement getLocationTextbox() {
        for (By locator : searchInputLocators) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (TimeoutException ignored) {
            }
        }
        throw new TimeoutException("Could not locate search input using provided locators.");
    }

    public void searchLocation(String location) {
        WebElement box = getLocationTextbox();
        box.clear();
        box.sendKeys(location);

        WebElement searchBtn = null;
        for (By locator : searchButtonLocators) {
            try {
                searchBtn = wait.until(ExpectedConditions.elementToBeClickable(locator));
                break;
            } catch (TimeoutException ignored) {
            }
        }

        if (searchBtn == null) {
            throw new TimeoutException("Could not locate 'Search for Stores' button using provided locators.");
        }

        try {
            searchBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBtn);
        }
    }
}
