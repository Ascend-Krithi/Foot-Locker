package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004_VerifyBostonStoreAddress extends BaseTest {
    
    @Test(description="Test Case - SCRUM-17166 TS-001 TC-004: Search for stores in Boston MA, verify specific store address 375 Washington Street")
    public void testVerifyBostonStoreAddress() {
        homePage.acceptCookies();
        
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        
        storeLocatorHelper.waitForStoreLocatorToLoad();
        storeLocatorHelper.searchForStores("Boston, MA");
        
        String expectedAddress = "375 Washington Street, Boston, MA 02108";
        
        Assert.assertTrue(storeLocatorHelper.isStoreInResults(expectedAddress), "Store with address 375 Washington Street, Boston, MA 02108 should be visible in results");
    }
}