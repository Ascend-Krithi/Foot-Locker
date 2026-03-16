package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004 extends BaseTest {
    
    @Test(description = "TC 4203: Verify specific store address 375 Washington Street Boston MA 02108 in results")
    public void verifySpecificStoreAddress() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage();
        
        storeLocator.clickFindAStore();
        
        storeLocator.clickSelectMyStore();
        
        storeLocator.enterLocation("Boston, MA");
        
        storeLocator.clickSearchForStores();
        
        Assert.assertTrue(storeLocator.isSpecificStoreAddressVisible("375 Washington Street"), 
            "Store with address 375 Washington Street, Boston, MA 02108 should be visible");
    }
}