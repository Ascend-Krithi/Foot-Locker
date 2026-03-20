package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fl.automation.core.BaseTest;

public class TS001_TC005_ClickSetMyStoreForWashingtonStreet extends BaseTest {
    
    @Test(description = "TC_4204: Search Boston MA, locate 375 Washington Street, click Set My Store")
    public void testClickSetMyStoreForWashingtonStreet() {
        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        storeLocatorHelper.waitForStoreLocatorToLoad();
        storeLocatorHelper.enterLocation("Boston MA");
        storeLocatorHelper.clickSearchForStores();
        Assert.assertTrue(storeLocatorHelper.isStoreDisplayed("375 Washington Street"), "Store at 375 Washington Street should be visible");
        storeLocatorHelper.clickSetMyStore();
        Assert.assertTrue(homePage.isOnFootLockerDomain(), "Should remain on Foot Locker domain after setting store");
    }
}