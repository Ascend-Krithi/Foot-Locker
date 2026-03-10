package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC005__02 extends BaseTest {
    @Test(description = "TS-001 TC-005 - Verify exact store address")
    public void testVerifyExactStoreAddress() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        helper.enterLocationAndSearch("Boston, MA");
        Assert.assertTrue(helper.verifyStoreAddressInResults("375 Washington Street, Boston, MA 02108"), "Exact store address should be present");
    }
}
