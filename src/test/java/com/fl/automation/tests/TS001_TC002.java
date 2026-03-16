package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002 extends BaseTest {
    
    @Test(description = "TC 4201: Verify Find a Store popup contains Location textbox and Search button")
    public void verifyLocationTextboxAndSearchButton() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage();
        
        storeLocator.clickFindAStore();
        
        storeLocator.clickSelectMyStore();
        
        Assert.assertTrue(storeLocator.isLocationTextboxPresent(), 
            "Location textbox should be present");
        
        Assert.assertTrue(storeLocator.isSearchButtonPresent(), 
            "Search for Stores button should be present");
    }
}