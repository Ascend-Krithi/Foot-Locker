package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fl.automation.core.BaseTest;

public class TS001_TC007_VerifyStorePersistsOnSneakersPage extends BaseTest {
    
    @Test(description = "TC_4206: Set 375 Washington Street as preferred, navigate to sneakers page, verify store persists")
    public void testVerifyStorePersistsOnSneakersPage() {
        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        storeLocatorHelper.waitForStoreLocatorToLoad();
        storeLocatorHelper.enterLocation("Boston MA");
        storeLocatorHelper.clickSearchForStores();
        storeLocatorHelper.clickSetMyStore();
        homePage.navigateToSneakersPage();
        Assert.assertTrue(storeLocatorHelper.isStoreNameInHeader("Washington"), "Store name should persist in header after navigating to sneakers page");
        Assert.assertTrue(homePage.isOnFootLockerDomain(), "Should remain on Foot Locker domain");
    }
}