package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_SCRUM17166 extends BaseTest {

    @Test(description = "TC 3193 - Verify Find a Store link and popup with Select My Store link")
    public void testFindAStorePopupWithSelectMyStore() {
        homePage.acceptCookiesIfPresent();
        homePage.closeModalIfPresent();
        
        homePage.clickFindAStore();
        
        boolean isSelectMyStoreVisible = storeLocatorHelper.isSelectMyStoreVisible();
        Assert.assertTrue(isSelectMyStoreVisible, "Select My Store link should be visible in the popup");
    }
}