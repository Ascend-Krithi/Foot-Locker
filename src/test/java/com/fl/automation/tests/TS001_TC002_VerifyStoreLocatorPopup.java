package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_VerifyStoreLocatorPopup extends BaseTest {

    @Test(description = "TC4201 - TS-001 TC-002: Click Find a Store, click Select My Store, verify Location textbox and Search for Stores button")
    public void testVerifyStoreLocatorPopup() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        // Step 1: Handle cookies
        homePage.acceptCookiesIfPresent();

        // Step 2: Click "Find a Store" → opens dropdown
        homePage.clickFindStore();

        // Step 3: Click "Select my store" → opens the Find a Store modal
        homePage.clickSelectMyStore();

        // Step 4: Wait for the search input inside the modal to be ready
        storeLocatorHelper.waitForStoreLocatorToLoad();

        // Step 5: Enter city as "Boston" (post code ignored)
        storeLocatorHelper.enterLocation("Boston");

        // Step 6: Click Search for Stores
        storeLocatorHelper.clickSearchButton();

        // Step 7: Assertions
        Assert.assertTrue(
            storeLocatorHelper.isLocationSearchInputDisplayed(),
            "Location input should be displayed"
        );
        Assert.assertTrue(
            storeLocatorHelper.isSearchButtonDisplayed(),
            "'Search for Stores' button should be displayed"
        );
    }
}
