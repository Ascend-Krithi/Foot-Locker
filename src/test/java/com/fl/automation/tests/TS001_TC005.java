package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC005 extends BaseTest {
    
    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-005")
    public void testSetMyStoreForBostonLocation() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.dismissCookieConsent();
        homePage.closeModalIfPresent();
        
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        
        storeLocator.enterLocation("Boston, MA");
        storeLocator.clickSearchStores();
        
        boolean isStoreVisible = storeLocator.isStoreAddressVisible("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(isStoreVisible, "Store with address '375 Washington Street, Boston, MA 02108' should be listed");
        
        storeLocator.clickSetMyStoreForAddress("375 Washington Street, Boston, MA 02108");
        
        boolean isStoreSet = storeLocator.isStoreSetAsPreferred("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(isStoreSet, "Selected store should be saved as user's preferred store");
    }
}