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

    private By locationTextbox = By.cssSelector("input[placeholder*='Enter address' i]");
    private By locationTextboxFallback1 = By.cssSelector("input[aria-label*='Location' i]");
    private By locationTextboxFallback2 = By.cssSelector("input[name*='location' i]");
    private By searchButton = By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]");
    private By searchButtonFallback1 = By.cssSelector("[aria-label*='Search for store' i]");
    private By searchButtonFallback2 = By.cssSelector("button[type='submit']");

    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void enterLocation(String location) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox));
            element.clear();
            element.sendKeys(location);
        } catch (Exception e1) {
            try {
                WebElement element = driver.findElement(locationTextboxFallback1);
                element.clear();
                element.sendKeys(location);
            } catch (Exception e2) {
                WebElement element = driver.findElement(locationTextboxFallback2);
                element.clear();
                element.sendKeys(location);
            }
        }
    }

    public void clickSearchButton() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            element.click();
        } catch (Exception e1) {
            try {
                WebElement element = driver.findElement(searchButtonFallback1);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } catch (Exception e2) {
                WebElement element = driver.findElement(searchButtonFallback2);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
        }
    }

    public boolean isLocationTextboxVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locationTextbox)).isDisplayed();
        } catch (Exception e1) {
            try {
                return driver.findElement(locationTextboxFallback1).isDisplayed();
            } catch (Exception e2) {
                try {
                    return driver.findElement(locationTextboxFallback2).isDisplayed();
                } catch (Exception e3) {
                    return false;
                }
            }
        }
    }

    public boolean isSearchButtonVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton)).isDisplayed();
        } catch (Exception e1) {
            try {
                return driver.findElement(searchButtonFallback1).isDisplayed();
            } catch (Exception e2) {
                try {
                    return driver.findElement(searchButtonFallback2).isDisplayed();
                } catch (Exception e3) {
                    return false;
                }
            }
        }
    }
}