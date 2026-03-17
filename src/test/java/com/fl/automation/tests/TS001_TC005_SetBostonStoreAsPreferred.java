package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC005_SetBostonStoreAsPreferred extends BaseTest {

    @Test(description = "TC4204 - TS-001 TC-005: Search Boston MA, locate 375 Washington Street store, click Set My Store")
    public void testSetBostonStoreAsPreferred() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        homePage.acceptCookiesIfPresent();
        
        homePage.clickFindStore();
        
        homePage.clickSelectMyStore();
        
        storeLocatorHelper.enterLocation("Boston MA");
        
        storeLocatorHelper.clickSearchButton();
        
        Assert.assertTrue(storeLocatorHelper.isSpecificStoreDisplayed("375 Washington Street"), "Store at 375 Washington Street should be visible");
        
        storeLocatorHelper.clickSetMyStoreForAddress("375 Washington Street");
        
        Assert.assertTrue(storeLocatorHelper.isStoreConfirmationDisplayed(), "Store should be set successfully");
    }
}