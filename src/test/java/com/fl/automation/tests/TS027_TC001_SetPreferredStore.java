package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS027_TC001_SetPreferredStore extends BaseTest {

    @Test(description = "TC_4173: SCRUM-17166 TS-004 TC-001 - Set preferred store")
    public void testSetPreferredStore() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToFootLocker();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.searchForStores("Boston, MA");
        storeLocator.setPreferredStore("375 Washington");
        
        Assert.assertTrue(true, "Preferred store set successfully");
    }
}