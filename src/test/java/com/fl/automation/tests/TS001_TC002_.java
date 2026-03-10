package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS001_TC002_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-002: Verify store locator popup with Location textbox and Search button")
    public void testStoreLocatorPopupElements() {
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        By[] searchInputLocators = {
            By.cssSelector("input[type='search']"),
            By.cssSelector("input[name='q']"),
            By.cssSelector("input[aria-label*='Search']"),
            By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]")
        };
        
        WebElement searchInput = null;
        for (By locator : searchInputLocators) {
            try {
                searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                break;
            } catch (Exception e) {
                continue;
            }
        }
        
        Assert.assertNotNull(searchInput, "Location textbox should be present in store locator popup");
        Assert.assertTrue(searchInput.isDisplayed(), "Location textbox should be visible");
        
        By[] searchButtonLocators = {
            By.xpath("//button[contains(.,'Search for Stores') or contains(.,'Search Stores') or contains(.,'Find Stores')]"),
            By.cssSelector("button[type='submit']"),
            By.xpath("//button[@type='submit']")
        };
        
        WebElement searchButton = null;
        for (By locator : searchButtonLocators) {
            try {
                searchButton = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                break;
            } catch (Exception e) {
                continue;
            }
        }
        
        Assert.assertNotNull(searchButton, "Search for Stores button should be present in store locator popup");
        Assert.assertTrue(searchButton.isDisplayed(), "Search for Stores button should be visible");
    }
}