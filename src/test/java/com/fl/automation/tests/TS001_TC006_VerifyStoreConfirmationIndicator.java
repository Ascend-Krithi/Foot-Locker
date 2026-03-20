package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fl.automation.core.BaseTest;

public class TS001_TC006_VerifyStoreConfirmationIndicator extends BaseTest {
    
    @Test(description = "TC_4205: Set 375 Washington Street as preferred, verify confirmation indicator")
    public void testVerifyStoreConfirmationIndicator() {
        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        storeLocatorHelper.waitForStoreLocatorToLoad();
        storeLocatorHelper.enterLocation("Boston MA");
        storeLocatorHelper.clickSearchForStores();
        storeLocatorHelper.clickSetMyStore();
        Assert.assertTrue(storeLocatorHelper.isStoreConfirmationDisplayed(), "Store confirmation indicator should be displayed after setting preferred store");
    }
}