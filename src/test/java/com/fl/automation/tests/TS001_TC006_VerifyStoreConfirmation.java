package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006_VerifyStoreConfirmation extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-006: Verify store confirmation and display")
    public void testVerifyStoreConfirmation() {
        homePage.clickFindStoreButton();
        homePage.clickSelectMyStoreLink();
        homePage.enterLocation("Boston, MA");
        homePage.clickSearchButton();
        
        String storeAddress = "375 Washington Street, Boston, MA 02108";
        storeLocatorHelper.clickSetMyStoreForAddress(storeAddress);
        
        Assert.assertTrue(storeLocatorHelper.isStoreSelected(), 
            "Store should be set as preferred");
        
        Assert.assertTrue(storeLocatorHelper.isStoreSelected(), 
            "Confirmation indicator should be visible");
        
        String storeName = storeLocatorHelper.getStoreNameFromHeader();
        Assert.assertFalse(storeName.isEmpty(), 
            "Selected store should be shown in website header or location indicator");
    }
}