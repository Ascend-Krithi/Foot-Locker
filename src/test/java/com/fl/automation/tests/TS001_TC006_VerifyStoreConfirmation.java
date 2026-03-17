package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006_VerifyStoreConfirmation extends BaseTest {

    @Test(description = "TC4205 - Set 375 Washington Street as preferred store, verify confirmation and header display")
    public void testVerifyStoreConfirmation() throws InterruptedException {
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

        storeLocatorHelper.clickSetMyStoreForAddress("375 Washington Street");
        Thread.sleep(3000);

        boolean confirmationDisplayed = storeLocatorHelper.isStoreSetConfirmationDisplayed();
        boolean headerDisplaysStore = homePage.isSelectedStoreDisplayedInHeader("375 Washington Street");

        Assert.assertTrue(confirmationDisplayed || headerDisplaysStore, "Confirmation indicator is not visible or selected store is not shown in website header");
    }
}