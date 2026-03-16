package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006 extends BaseTest {

    @Test(description = "TC4205 - TS-001 TC-006: Set 375 Washington Street as preferred, verify confirmation indicator and header display")
    public void testVerifyStoreConfirmationAndHeaderDisplay() {
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

        // Verify confirmation is displayed
        boolean isConfirmationDisplayed = storeLocatorHelper.isStoreConfirmationDisplayed();
        Assert.assertTrue(isConfirmationDisplayed, "Store confirmation should be displayed");

        // Verify store is displayed in header
        boolean isStoreInHeader = storeLocatorHelper.isSelectedStoreDisplayedInHeader("375 Washington Street");
        Assert.assertTrue(isStoreInHeader, "Selected store should be displayed in header");
    }
}