package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_VerifyStoreLocatorPopup extends BaseTest {

    @Test(description = "TC4201 - TS-001 TC-002: Click Find a Store, verify Location textbox and Search for Stores button")
    public void testVerifyStoreLocatorPopup() {

        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        // Step 1: Handle cookies
        homePage.acceptCookiesIfPresent();

        // Step 2: Click "Find a Store" — opens the store locator popup
        // and waits internally for the popup header to appear
        homePage.clickFindStore();

        // Step 3: Wait for search input inside the popup to be ready
        // NOTE: clickSelectMyStore() was REMOVED — it was clicking a second time
        // inside the popup which was dismissing it or changing its state,
        // causing the search input to never appear. The input is already
        // visible after clickFindStore() succeeds.
        storeLocatorHelper.waitForStoreLocatorToLoad();

        // Step 4: Assertions
        Assert.assertTrue(
            storeLocatorHelper.isLocationSearchInputDisplayed(),
            "Location search input should be displayed inside the store locator popup"
        );

        Assert.assertTrue(
            storeLocatorHelper.isSearchButtonDisplayed(),
            "Search for Stores button should be displayed inside the store locator popup"
        );
    }
}
