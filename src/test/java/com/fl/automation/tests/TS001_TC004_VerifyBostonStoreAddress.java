package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004_VerifyBostonStoreAddress extends BaseTest {

    @Test(description = "TC4203 - Search Boston MA, verify store at 375 Washington Street Boston MA 02108 is visible")
    public void testVerifyBostonStoreAddress() throws InterruptedException {
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

        Assert.assertTrue(storeLocatorHelper.areStoreResultsDisplayed(), "Store results are not displayed");

        Assert.assertTrue(storeLocatorHelper.isStoreAddressVisible("375 Washington Street"), "Store with address 375 Washington Street, Boston, MA 02108 is not visible in the results");
    }
}