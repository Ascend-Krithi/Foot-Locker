package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_VerifyStoreLocatorPopup extends BaseTest {

    @Test(description = "TC4201 - Verify Location textbox and Search for Stores button")
    public void testVerifyStoreLocatorPopup() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        storeLocatorHelper.waitForStoreLocatorToLoad();
        
        Assert.assertTrue(storeLocatorHelper.isLocationSearchInputDisplayed(), 
            "Location textbox should be displayed");
        Assert.assertTrue(storeLocatorHelper.isSearchButtonDisplayed(), 
            "Search for Stores button should be displayed");
    }
}