package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002 extends BaseTest {

    @Test(description = "TC4201 - TS-001 TC-002: Click Find a Store, click Select My Store, verify Location textbox and Search button")
    public void testVerifyLocationTextboxAndSearchButton() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        // Click Find a Store
        homePage.clickFindStore();

        // Click Select My Store
        homePage.clickSelectMyStore();

        // Verify Location textbox is visible
        boolean isLocationTextboxVisible = storeLocatorHelper.isLocationTextboxVisible();
        Assert.assertTrue(isLocationTextboxVisible, "Location textbox should be visible");

        // Verify Search button is visible
        boolean isSearchButtonVisible = storeLocatorHelper.isSearchButtonVisible();
        Assert.assertTrue(isSearchButtonVisible, "Search button should be visible");
    }
}