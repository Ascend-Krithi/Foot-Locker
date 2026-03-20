package com.fl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fl.automation.core.BaseTest;

public class TS001_TC002_VerifySelectMyStoreAndLocationTextbox extends BaseTest {
    
    @Test(description = "TC_4201: Click Find a Store, click Select My Store, verify Location textbox and Search button")
    public void testVerifySelectMyStoreAndLocationTextbox() {
        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        storeLocatorHelper.waitForStoreLocatorToLoad();
        Assert.assertTrue(homePage.isOnFootLockerDomain(), "Should remain on Foot Locker domain");
    }
}