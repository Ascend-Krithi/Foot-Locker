package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.fl.automation.listeners.TestListener.class)
public class TS001_TC006_VerifyStoreConfirmation extends BaseTest {

    @Test(description = "TC_ID: 226 - Verify store confirmation after setting preferred store")
    public void testVerifyStoreConfirmation() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        storeLocatorHelper.waitForStoreLocatorSearchInput();
        storeLocatorHelper.searchForLocation("Boston MA");
        storeLocatorHelper.clickSearchButton();
        storeLocatorHelper.waitForStoreResults();
        storeLocatorHelper.clickSetMyStoreForStore();
        Assert.assertTrue(storeLocatorHelper.isStoreConfirmationDisplayed(), "Store confirmation should be displayed");
    }
}