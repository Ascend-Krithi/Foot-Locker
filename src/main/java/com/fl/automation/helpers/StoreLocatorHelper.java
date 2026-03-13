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
    
    private static final List<By> LOCATOR_INPUT = Arrays.asList(
        By.cssSelector("input[placeholder*='Enter address' i]"),
        By.cssSelector("input[aria-label*='Location' i]"),
        By.cssSelector("input[name*='location' i]")
    );
    
    private static final List<By> LOCATOR_SEARCH_BTN = Arrays.asList(
        By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]"),
        By.cssSelector("[aria-label*='Search for store' i]"),
        By.cssSelector("button[type='submit']")
    );
    
    public StoreLocatorHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }
    
    public void searchStore(String location) {
        WebElement input = findElementWithFallback(LOCATOR_INPUT);
        if (input != null) {
            input.clear();
            input.sendKeys(location);
            
            WebElement searchBtn = findElementWithFallback(LOCATOR_SEARCH_BTN);
            if (searchBtn != null) {
                clickWithJsFallback(searchBtn);
            }
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
        return null;
    }
    
    private void clickWithJsFallback(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}