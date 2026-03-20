package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004_VerifyBostonStoreAddress extends BaseTest {

    @Test(description = "TC4203 - Verify store at 375 Washington Street Boston MA 02108")
    public void testVerifyBostonStoreAddress() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        storeLocatorHelper.waitForStoreLocatorToLoad();
        
        storeLocatorHelper.enterLocation("Boston MA");
        storeLocatorHelper.clickSearchButton();
        storeLocatorHelper.waitForStoreResults();
        
        Assert.assertTrue(storeLocatorHelper.isStoreDisplayed("375 Washington Street"), 
            "Store with address 375 Washington Street should be visible in results");
    }
}