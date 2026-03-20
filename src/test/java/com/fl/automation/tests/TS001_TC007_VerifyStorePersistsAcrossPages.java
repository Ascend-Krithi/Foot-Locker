package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC007_VerifyStorePersistsAcrossPages extends BaseTest {
    
    @Test(description="Test Case - SCRUM-17166 TS-001 TC-007: Set 375 Washington Street as preferred store, navigate to sneakers page, verify store persists")
    public void testVerifyStorePersistsAcrossPages() {
        homePage.acceptCookies();
        
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        
        storeLocatorHelper.waitForStoreLocatorToLoad();
        storeLocatorHelper.searchForStores("Boston, MA");
        
        String storeAddress = "375 Washington Street, Boston, MA 02108";
        
        storeLocatorHelper.clickSetMyStore(storeAddress);
        
        Assert.assertTrue(storeLocatorHelper.isStoreSelected(storeAddress), "Store should be set as preferred");
        
        homePage.navigateToSneakersPage();
        
        Assert.assertTrue(storeLocatorHelper.isStoreDisplayedInHeader("Boston"), "Selected store (375 Washington Street, Boston, MA 02108) should still be shown as preferred after navigation");
    }
}