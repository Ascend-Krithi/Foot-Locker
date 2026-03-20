package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC007_VerifyStorePersistence extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-007: Verify selected store persists across navigation")
    public void testVerifyStorePersistence() {
        homePage.clickFindStoreButton();
        homePage.clickSelectMyStoreLink();
        homePage.enterLocation("Boston, MA");
        homePage.clickSearchButton();
        
        String storeAddress = "375 Washington Street, Boston, MA 02108";
        storeLocatorHelper.clickSetMyStoreForAddress(storeAddress);
        
        Assert.assertTrue(storeLocatorHelper.isStoreSelected(), 
            "Store should be set as preferred");
        
        homePage.navigateToSneakersPage();
        
        Assert.assertTrue(homePage.isOnFootLockerDomain(), 
            "Page should load successfully");
        
        Assert.assertTrue(storeLocatorHelper.isStoreNameInHeader("Boston") || 
                         storeLocatorHelper.isStoreNameInHeader("Washington"), 
            "Selected store (375 Washington Street, Boston, MA 02108) should still be shown as preferred");
    }
}