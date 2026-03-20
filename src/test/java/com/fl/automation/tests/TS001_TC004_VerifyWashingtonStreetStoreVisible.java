package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fl.automation.core.BaseTest;

public class TS001_TC004_VerifyWashingtonStreetStoreVisible extends BaseTest {
    
    @Test(description = "TC_4203: Search Boston MA, verify store at 375 Washington Street is visible")
    public void testVerifyWashingtonStreetStoreVisible() {
        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        storeLocatorHelper.waitForStoreLocatorToLoad();
        storeLocatorHelper.enterLocation("Boston MA");
        storeLocatorHelper.clickSearchForStores();
        Assert.assertTrue(storeLocatorHelper.isStoreDisplayed("375 Washington Street"), "Store at 375 Washington Street should be visible in search results");
    }
}