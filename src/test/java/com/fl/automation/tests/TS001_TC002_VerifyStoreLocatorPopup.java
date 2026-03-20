package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_VerifyStoreLocatorPopup extends BaseTest {

    @Test(description = "TC4201 - TS-001 TC-002: Verify Location textbox and Search button in Store Locator popup")
    public void testVerifyStoreLocatorPopup() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();

        storeLocatorHelper.waitForStoreLocatorToLoad();
        Assert.assertTrue(storeLocatorHelper.isLocationSearchInputDisplayed(), "Location input should be displayed");
        Assert.assertTrue(storeLocatorHelper.isSearchButtonDisplayed(), "Search for Stores button should be displayed");
    }
}
