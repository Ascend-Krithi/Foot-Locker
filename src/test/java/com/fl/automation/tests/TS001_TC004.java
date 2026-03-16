package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004 extends BaseTest {

    @Test(description = "TC4203 - TS-001 TC-004: Search Boston MA, verify store at 375 Washington Street is visible")
    public void testVerifySpecificStoreInBostonMA() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        // Click Find a Store
        homePage.clickFindStore();

        // Click Select My Store
        homePage.clickSelectMyStore();

        // Enter location
        storeLocatorHelper.enterLocation("Boston MA");

        // Click Search button
        storeLocatorHelper.clickSearchButton();

        // Verify specific store address is visible
        boolean isStoreVisible = storeLocatorHelper.isStoreAddressVisible("375 Washington Street");
        Assert.assertTrue(isStoreVisible, "Store at 375 Washington Street should be visible in search results");
    }
}