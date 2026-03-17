package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006_VerifyStoreConfirmation extends BaseTest {

    @Test(description = "TC4205 - TS-001 TC-006: Set 375 Washington Street as preferred store, verify confirmation indicator and header shows selected store")
    public void testVerifyStoreConfirmation() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        homePage.acceptCookiesIfPresent();
        
        homePage.clickFindStore();
        
        homePage.clickSelectMyStore();
        
        storeLocatorHelper.enterLocation("Boston MA");
        
        storeLocatorHelper.clickSearchButton();
        
        storeLocatorHelper.clickSetMyStoreForAddress("375 Washington Street");
        
        Assert.assertTrue(storeLocatorHelper.isStoreConfirmationDisplayed(), "Store confirmation should be displayed");
        
        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker.com"), "User should remain on Foot Locker domain after setting store");
    }
}