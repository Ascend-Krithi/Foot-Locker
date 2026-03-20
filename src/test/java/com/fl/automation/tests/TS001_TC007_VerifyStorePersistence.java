package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC007_VerifyStorePersistence extends BaseTest {

    @Test(description = "TC4206 - TS-001 TC-007: Verify store persistence across pages")
    public void testVerifyStorePersistence() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();

        storeLocatorHelper.waitForStoreLocatorToLoad();
        storeLocatorHelper.searchForLocation("Boston, MA");
        storeLocatorHelper.clickSearchButton();

        storeLocatorHelper.clickSetMyStoreForStore("375 Washington Street");
        Assert.assertTrue(storeLocatorHelper.isStoreNameInHeader("Boston"), "Store should be set in header");

        homePage.navigateToSneakersPage();
        Assert.assertTrue(homePage.isOnFootLockerDomain(), "Should still be on Foot Locker domain");
        Assert.assertTrue(storeLocatorHelper.isStoreNameInHeader("Boston"), "Selected store should persist on sneakers page");
    }
}
