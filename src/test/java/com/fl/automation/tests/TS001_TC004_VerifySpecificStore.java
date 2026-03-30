package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.fl.automation.listeners.TestListener.class)
public class TS001_TC004_VerifySpecificStore extends BaseTest {

    @Test(description = "TC_ID: 224 - Verify specific store at 375 Washington Street")
    public void testVerifySpecificStore() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        storeLocatorHelper.waitForStoreLocatorSearchInput();
        storeLocatorHelper.searchForLocation("Boston MA");
        storeLocatorHelper.clickSearchButton();
        storeLocatorHelper.waitForStoreResults();
        Assert.assertTrue(storeLocatorHelper.isStoreDisplayed("375 Washington Street"), "Store at 375 Washington Street should be present");
    }
}