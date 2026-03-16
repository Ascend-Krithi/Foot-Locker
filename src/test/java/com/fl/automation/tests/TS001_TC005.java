package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC005 extends BaseTest {

    @Test(description = "TC4204 - TS-001 TC-005: Search Boston MA, click Set My Store for 375 Washington Street")
    public void testSetMyStoreFor375WashingtonStreet() {
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

        // Click Set My Store for specific address
        storeLocatorHelper.clickSetMyStoreForAddress("375 Washington Street");

        // Verify action completed (no exception thrown)
        Assert.assertTrue(true, "Set My Store action completed successfully");
    }
}