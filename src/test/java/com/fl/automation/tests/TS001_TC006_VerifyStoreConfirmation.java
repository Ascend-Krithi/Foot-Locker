package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006_VerifyStoreConfirmation extends BaseTest {

    @Test(description = "TC4205 - TS-001 TC-006: Verify store confirmation indicator")
    public void testVerifyStoreConfirmation() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();

        storeLocatorHelper.waitForStoreLocatorToLoad();
        storeLocatorHelper.searchForLocation("Boston, MA");
        storeLocatorHelper.clickSearchButton();

        storeLocatorHelper.clickSetMyStoreForStore("375 Washington Street");

        Assert.assertTrue(storeLocatorHelper.isStoreConfirmationDisplayed(), "Confirmation indicator should be visible");
        Assert.assertTrue(storeLocatorHelper.isStoreNameInHeader("Boston"), "Selected store should appear in header");
    }
}
