package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006_VerifyStoreConfirmation extends BaseTest {

    @Test(description = "TC4205 - Verify confirmation indicator for preferred store")
    public void testVerifyStoreConfirmation() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        storeLocatorHelper.waitForStoreLocatorToLoad();
        
        storeLocatorHelper.enterLocation("Boston MA");
        storeLocatorHelper.clickSearchButton();
        storeLocatorHelper.waitForStoreResults();
        storeLocatorHelper.clickSetMyStoreForStore("375 Washington Street");
        
        Assert.assertTrue(storeLocatorHelper.isStoreConfirmationDisplayed(), 
            "Store confirmation indicator should be displayed");
    }
}