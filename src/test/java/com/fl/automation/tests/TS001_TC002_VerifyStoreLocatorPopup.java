package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.fl.automation.listeners.TestListener.class)
public class TS001_TC002_VerifyStoreLocatorPopup extends BaseTest {

    @Test(description = "TC_ID: 222 - Verify Store Locator popup elements")
    public void testVerifyStoreLocatorPopup() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        storeLocatorHelper.waitForStoreLocatorSearchInput();
        Assert.assertTrue(storeLocatorHelper.isLocationSearchInputDisplayed(), "Location textbox should be present");
    }
}