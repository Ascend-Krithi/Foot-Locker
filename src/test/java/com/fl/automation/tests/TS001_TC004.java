package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004 extends BaseTest {

    @Test(description = "TC-4203: Verify specific store address 375 Washington Street, Boston, MA 02108")
    public void testVerifySpecificStoreAddress() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);
        
        String expectedAddress = "375 Washington Street, Boston, MA 02108";
        
        homePage.navigateToHomePage();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        homePage.enterLocation("Boston, MA");
        homePage.clickSearchForStores();
        
        boolean isAddressVisible = storeHelper.isStoreAddressVisible(expectedAddress);
        Assert.assertTrue(isAddressVisible, "Store with address '" + expectedAddress + "' should be visible in results");
        
        String actualAddress = storeHelper.getStoreAddress("375 Washington Street");
        Assert.assertNotNull(actualAddress, "Store address should be found");
        Assert.assertTrue(actualAddress.contains("375 Washington Street"), "Address should contain '375 Washington Street'");
        Assert.assertTrue(actualAddress.contains("Boston"), "Address should contain 'Boston'");
        Assert.assertTrue(actualAddress.contains("MA"), "Address should contain 'MA'");
        Assert.assertTrue(actualAddress.contains("02108"), "Address should contain '02108'");
    }
}