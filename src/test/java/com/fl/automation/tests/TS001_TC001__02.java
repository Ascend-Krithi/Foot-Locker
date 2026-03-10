package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001__02 extends BaseTest {
    @Test(description = "TS-001 TC-001 - Launch app and verify Find a Store popup")
    public void testLaunchAndFindStorePopup() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        Assert.assertTrue(helper.isFindStorePopupVisible(), "Find a Store popup should be visible on launch");
    }
}
