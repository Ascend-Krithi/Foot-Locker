package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006 extends BaseTest {

    @Test(description = "TC-4205: Verify store selection confirmation and header display")
    public void testStoreSelectionConfirmation() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);
        
        String targetAddress = "375 Washington Street";
        String storeIdentifier = "Boston";
        
        homePage.navigateToHomePage();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        homePage.enterLocation("Boston, MA");
        homePage.clickSearchForStores();
        storeHelper.setMyStoreByAddress(targetAddress);
        
        boolean isConfirmationDisplayed = storeHelper.isStoreSetConfirmationDisplayed();
        Assert.assertTrue(isConfirmationDisplayed, "Store selection confirmation should be displayed");
        
        boolean isStoreInHeader = storeHelper.isStoreDisplayedInHeader(storeIdentifier);
        Assert.assertTrue(isStoreInHeader, "Selected store should appear in website header/location indicator");
    }
}