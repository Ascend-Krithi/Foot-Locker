package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004_VerifyBostonStoreAddress extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-004: Verify specific Boston store address in results")
    public void testVerifyBostonStoreAddress() {
        homePage.clickFindStoreButton();
        homePage.clickSelectMyStoreLink();
        homePage.enterLocation("Boston, MA");
        homePage.clickSearchButton();
        
        Assert.assertTrue(homePage.areStoreResultsDisplayed(), 
            "Store results should be displayed");
        
        String expectedAddress = "375 Washington Street, Boston, MA 02108";
        Assert.assertTrue(storeLocatorHelper.isStoreAddressPresent(expectedAddress), 
            "Store with address 375 Washington Street, Boston, MA 02108 should be visible in the results");
        
        Assert.assertTrue(storeLocatorHelper.isStoreAddressPresent(expectedAddress), 
            "Store address should match exactly with 375 Washington Street, Boston, MA 02108");
    }
}