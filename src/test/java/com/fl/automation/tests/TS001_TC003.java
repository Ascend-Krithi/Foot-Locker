package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003 extends BaseTest {

    @Test(description = "TC4202 - TS-001 TC-003: Search for stores in Boston MA, verify results displayed")
    public void testSearchStoresInBostonMA() {
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

        // Verify store results are displayed
        boolean areResultsDisplayed = storeLocatorHelper.areStoreResultsDisplayed();
        Assert.assertTrue(areResultsDisplayed, "Store results should be displayed for Boston MA");
    }
}