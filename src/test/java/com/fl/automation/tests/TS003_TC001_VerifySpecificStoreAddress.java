package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS003_TC001_VerifySpecificStoreAddress extends BaseTest {

    @Test(description = "TC_4172: SCRUM-17166 TS-003 TC-001 - Verify specific store address")
    public void verifySpecificStoreAddress() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage(baseUrl);
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.searchForStores("Boston, MA");
        
        Assert.assertTrue(storeLocator.verifyStoreAddressContains("Boston"), "Store address should contain Boston");
    }
}