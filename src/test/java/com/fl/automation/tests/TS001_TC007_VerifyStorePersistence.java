package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TS001_TC007_VerifyStorePersistence extends BaseTest {

    @Test(description = "TC4206 - TS-001 TC-007: Set 375 Washington Street as preferred, navigate to sneakers page, verify store remains set")
    public void testVerifyStorePersistence() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        
        homePage.acceptCookiesIfPresent();
        
        homePage.clickFindStore();
        
        homePage.clickSelectMyStore();
        
        storeLocatorHelper.enterLocation("Boston MA");
        
        storeLocatorHelper.clickSearchButton();
        
        storeLocatorHelper.clickSetMyStoreForAddress("375 Washington Street");
        
        try {
            WebElement sneakersLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'sneakers') or contains(text(), 'Sneakers')]"))
            );
            sneakersLink.click();
        } catch (Exception e) {
            driver.navigate().to("https://www.footlocker.com/category/mens/shoes/sneakers.html");
        }
        
        wait.until(ExpectedConditions.urlContains("footlocker.com"));
        
        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker.com"), "User should be on Foot Locker domain");
        
        Assert.assertTrue(storeLocatorHelper.isStoreConfirmationDisplayed(), "Store preference should persist after navigation");
    }
}