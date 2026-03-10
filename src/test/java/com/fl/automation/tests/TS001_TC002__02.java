package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002__02 extends BaseTest {
    @Test(description = "TS-001 TC-002 - Verify Select My Store link")
    public void testVerifySelectMyStoreLink() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        Assert.assertTrue(helper.clickSelectMyStore(), "Select My Store link should be present and clickable");
    }
}
