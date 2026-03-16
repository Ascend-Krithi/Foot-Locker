package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class TS001_TC002 extends BaseTest {
    
    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-002")
    public void testFindAStorePopupContainsLocationAndSearchButton() {
        HomePage homePage = new HomePage(driver);
        
        homePage.dismissCookieConsent();
        homePage.closeModalIfPresent();
        
        homePage.clickFindStore();
        
        boolean isPopupDisplayed = homePage.isStorePopupDisplayed();
        Assert.assertTrue(isPopupDisplayed, "Popup should appear below 'Find a Store'");
        
        homePage.clickSelectMyStore();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        
        By locationInput = By.cssSelector("input[type='search']");
        By locationInputAlt1 = By.cssSelector("input[name='q']");
        By locationInputAlt2 = By.cssSelector("input[aria-label*='Search']");
        By locationInputAlt3 = By.cssSelector("input[placeholder*='Search' i], input[placeholder*='City' i], input[placeholder*='ZIP' i]");
        
        boolean isLocationInputPresent = false;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
            isLocationInputPresent = true;
        } catch (Exception e1) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locationInputAlt1));
                isLocationInputPresent = true;
            } catch (Exception e2) {
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(locationInputAlt2));
                    isLocationInputPresent = true;
                } catch (Exception e3) {
                    try {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(locationInputAlt3));
                        isLocationInputPresent = true;
                    } catch (Exception e4) {
                    }
                }
            }
        }
        Assert.assertTrue(isLocationInputPresent, "'Location' textbox should be present");
        
        By searchButton = By.xpath("//*[self::button or self::a][contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search for store')]");
        By searchButtonAlt1 = By.cssSelector("[aria-label*='Search for store' i], button[type='submit']");
        By searchButtonAlt2 = By.cssSelector("button[aria-label*='Search' i]");
        By searchButtonAlt3 = By.xpath("//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'search')]");
        
        boolean isSearchButtonPresent = false;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
            isSearchButtonPresent = true;
        } catch (Exception e1) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(searchButtonAlt1));
                isSearchButtonPresent = true;
            } catch (Exception e2) {
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(searchButtonAlt2));
                    isSearchButtonPresent = true;
                } catch (Exception e3) {
                    try {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(searchButtonAlt3));
                        isSearchButtonPresent = true;
                    } catch (Exception e4) {
                    }
                }
            }
        }
        Assert.assertTrue(isSearchButtonPresent, "'Search for Stores' button should be present");
    }
}