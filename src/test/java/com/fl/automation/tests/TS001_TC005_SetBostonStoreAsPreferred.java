package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC005_SetBostonStoreAsPreferred extends BaseTest {

    @Test(description = "TC4204 - TS-001 TC-005: Set Boston store as preferred")
    public void testSetBostonStoreAsPreferred() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();

        storeLocatorHelper.waitForStoreLocatorToLoad();
        storeLocatorHelper.searchForLocation("Boston, MA");
        storeLocatorHelper.clickSearchButton();

        Assert.assertTrue(storeLocatorHelper.isStoreDisplayed("375 Washington Street"), "Store should be visible before setting");
        storeLocatorHelper.clickSetMyStoreForStore("375 Washington Street");

        Assert.assertTrue(storeLocatorHelper.isStoreConfirmationDisplayed(), "Store confirmation should be displayed");
    }
}
