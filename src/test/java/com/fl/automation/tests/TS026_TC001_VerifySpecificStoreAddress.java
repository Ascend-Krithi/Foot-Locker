package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS026_TC001_VerifySpecificStoreAddress extends BaseTest {

    @Test(description = "TC_4172: SCRUM-17166 TS-003 TC-001 - Verify specific store address 375 Washington Street")
    public void testVerifySpecificStoreAddress() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToFootLocker();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.searchForStores("Boston, MA");
        
        boolean isAddressFound = storeLocator.verifyStoreAddressContains("375 Washington");
        Assert.assertTrue(isAddressFound, "Store address 375 Washington Street should be found");
    }
}