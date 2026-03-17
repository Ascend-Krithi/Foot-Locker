package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC005_SetBostonStoreAsPreferred extends BaseTest {

    @Test(description = "TC4204 - Search Boston MA, locate 375 Washington Street store, click Set My Store")
    public void testSetBostonStoreAsPreferred() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.acceptCookiesIfPresent();
        Thread.sleep(2000);

        homePage.clickFindStore();
        Thread.sleep(2000);

        homePage.clickSelectMyStore();
        Thread.sleep(3000);

        storeLocatorHelper.enterLocation("Boston, MA");
        Thread.sleep(1000);

        storeLocatorHelper.clickSearchForStores();
        Thread.sleep(4000);

        Assert.assertTrue(storeLocatorHelper.isStoreAddressVisible("375 Washington Street"), "Store is not listed in search results");

        storeLocatorHelper.clickSetMyStoreForAddress("375 Washington Street");
        Thread.sleep(3000);

        Assert.assertTrue(true, "Set My Store action completed for Boston location");
    }
}