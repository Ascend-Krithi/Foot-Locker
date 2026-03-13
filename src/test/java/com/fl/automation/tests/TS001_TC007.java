package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC007 extends BaseTest {

    @Test(description = "TC-4206: Verify store preference persists across navigation")
    public void testStorePreferencePersistence() {
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
        
        driver.get("https://www.footlocker.com/sneakers");
        
        boolean isStoreStillSet = storeHelper.verifyStorePreferencePersists(storeIdentifier);
        Assert.assertTrue(isStoreStillSet, "Selected store should remain set as preferred after navigation to sneakers page");
    }
}