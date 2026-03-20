package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006_VerifyStoreConfirmationIndicator extends BaseTest {
    
    @Test(description="Test Case - SCRUM-17166 TS-001 TC-006: Set 375 Washington Street as preferred store, verify confirmation indicator and store appears in header")
    public void testVerifyStoreConfirmationIndicator() {
        homePage.acceptCookies();
        
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        
        storeLocatorHelper.waitForStoreLocatorToLoad();
        storeLocatorHelper.searchForStores("Boston, MA");
        
        String storeAddress = "375 Washington Street, Boston, MA 02108";
        
        storeLocatorHelper.clickSetMyStore(storeAddress);
        
        Assert.assertTrue(storeLocatorHelper.isStoreSelected(storeAddress), "Confirmation indicator should be visible");
        
        String selectedStore = storeLocatorHelper.getSelectedStoreFromHeader();
        Assert.assertTrue(selectedStore.contains("Boston") || selectedStore.contains("Washington"), "Selected store should be shown in website header or location indicator");
    }
}