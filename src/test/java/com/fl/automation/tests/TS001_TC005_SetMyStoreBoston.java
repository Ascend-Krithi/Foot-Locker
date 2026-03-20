package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC005_SetMyStoreBoston extends BaseTest {
    
    @Test(description="Test Case - SCRUM-17166 TS-001 TC-005: Search for stores in Boston MA, locate 375 Washington Street, click Set My Store")
    public void testSetMyStoreBoston() {
        homePage.acceptCookies();
        
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        
        storeLocatorHelper.waitForStoreLocatorToLoad();
        storeLocatorHelper.searchForStores("Boston, MA");
        
        String storeAddress = "375 Washington Street, Boston, MA 02108";
        
        Assert.assertTrue(storeLocatorHelper.isStoreInResults(storeAddress), "Store should be listed in search results");
        
        storeLocatorHelper.clickSetMyStore(storeAddress);
        
        Assert.assertTrue(storeLocatorHelper.isStoreSelected(storeAddress), "Selected store should be saved as user's preferred store");
    }
}