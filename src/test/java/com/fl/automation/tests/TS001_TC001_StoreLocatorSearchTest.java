package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_StoreLocatorSearchTest extends BaseTest {
    
    @Test(description = "Verify store locator search functionality with Boston MA location")
    public void testStoreLocatorSearch() {
        // Initialize page objects locally
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        // Step 1: Accept cookies if present
        homePage.acceptCookiesIfPresent();
        
        // Step 2: Click Find Store button
        homePage.clickFindStore();
        
        // Step 3: Click Select My Store
        homePage.clickSelectMyStore();
        
        // Step 4: Enter search location
        storeLocatorHelper.enterLocation("Boston MA");
        
        // Step 5: Click search button
        storeLocatorHelper.clickSearchButton();
        
        // Step 6: Verify store is displayed
        boolean isStoreDisplayed = storeLocatorHelper.isStoreDisplayed("375 Washington Street");
        Assert.assertTrue(isStoreDisplayed, "Store at 375 Washington Street should be displayed in search results");
    }
}