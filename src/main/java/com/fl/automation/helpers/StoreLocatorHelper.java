package com.fl.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreLocatorHelper {
    private WebDriver driver;
    private WebDriverWait wait;

    private By[] searchInputLocators = new By[] {
        By.cssSelector("input[type='search']"),
        By.cssSelector("input[name='q']"),
        By.cssSelector("input[aria-label*='Search']"),
        By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
    };

    private By storeResultCards = By.cssSelector("[data-qa='location'], .c-location-card, .location, [class*='location-card']");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public boolean isLocationTextboxVisible() {
        try {
            for (By locator : searchInputLocators) {
                try {
                    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                    if (element.isDisplayed()) {
                        return true;
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

    public void enterLocation(String location) {
        WebElement element = null;
        for (By locator : searchInputLocators) {
            try {
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                break;
            } catch (Exception e) {
                continue;
            }
        }
        if (element != null) {
            element.clear();
            element.sendKeys(location);
        }
    }

    public void clickSearchButton() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Search for Stores')]")));
            try {
                element.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean areStoreResultsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(storeResultCards)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}