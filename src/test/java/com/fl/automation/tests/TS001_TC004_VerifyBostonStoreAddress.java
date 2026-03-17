package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004_VerifyBostonStoreAddress extends BaseTest {

    @Test(description = "TC4203 - TS-001 TC-004: Search Boston MA, verify store at 375 Washington Street Boston MA 02108 is visible")
    public void testVerifyBostonStoreAddress() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        homePage.acceptCookiesIfPresent();
        
        homePage.clickFindStore();
        
        homePage.clickSelectMyStore();
        
        storeLocatorHelper.enterLocation("Boston MA");
        
        storeLocatorHelper.clickSearchButton();
        
        Assert.assertTrue(storeLocatorHelper.isSpecificStoreDisplayed("375 Washington Street"), "Store at 375 Washington Street Boston MA 02108 should be visible in results");
    }
}