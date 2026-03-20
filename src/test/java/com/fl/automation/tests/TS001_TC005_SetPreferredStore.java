package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC005_SetPreferredStore extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-005: Set preferred store for Boston location")
    public void testSetPreferredStore() {
        homePage.clickFindStoreButton();
        homePage.clickSelectMyStoreLink();
        homePage.enterLocation("Boston, MA");
        homePage.clickSearchButton();
        
        String storeAddress = "375 Washington Street, Boston, MA 02108";
        Assert.assertTrue(storeLocatorHelper.isStoreAddressPresent(storeAddress), 
            "Store should be listed in search results");
        
        storeLocatorHelper.clickSetMyStoreForAddress(storeAddress);
        
        Assert.assertTrue(storeLocatorHelper.isStoreSelected(), 
            "Selected store should be saved as user's preferred store");
    }
}