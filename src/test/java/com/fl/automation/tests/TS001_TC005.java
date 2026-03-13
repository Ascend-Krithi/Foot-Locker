package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.annotations.Test;

public class TS001_TC005 extends BaseTest {

    @Test(description = "TC-4204: Set 375 Washington Street, Boston, MA 02108 as preferred store")
    public void testSetPreferredStore() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);
        
        String targetAddress = "375 Washington Street";
        
        homePage.navigateToHomePage();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        homePage.enterLocation("Boston, MA");
        homePage.clickSearchForStores();
        
        storeHelper.setMyStoreByAddress(targetAddress);
    }
}