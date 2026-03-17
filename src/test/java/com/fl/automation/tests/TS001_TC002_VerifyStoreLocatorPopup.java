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

        // Step 2: Click "Find a Store" → opens dropdown with "Select my store"
        homePage.clickFindStore();

        // Step 3: Click "Select my store" → opens the Find a Store modal
        // with "Enter address, city or post code" input and "Search for Stores" button
        homePage.clickSelectMyStore();

        // Step 4: Wait for the search input inside the modal to be ready
        storeLocatorHelper.waitForStoreLocatorToLoad();

        // Step 5: Assertions — confirmed from live page screenshot
        Assert.assertTrue(
            storeLocatorHelper.isLocationSearchInputDisplayed(),
            "Location input (placeholder: 'Enter address, city or post code') should be displayed"
        );

        Assert.assertTrue(
            storeLocatorHelper.isSearchButtonDisplayed(),
            "'Search for Stores' button should be displayed"
        );
    }
}
