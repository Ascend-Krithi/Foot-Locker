package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.fl.automation.listeners.TestListener.class)
public class TS001_TC007_VerifyStorePersistence extends BaseTest {

    @Test(description = "TC_ID: 227 - Verify store persists after navigation")
    public void testVerifyStorePersistence() {
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
        driver.navigate().refresh();
        Assert.assertTrue(storeLocatorHelper.isStoreNameInHeader(), "Store at 375 Washington Street should remain set as preferred");
    }
}