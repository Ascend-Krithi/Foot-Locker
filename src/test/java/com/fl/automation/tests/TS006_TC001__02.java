package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS006_TC001__02 extends BaseTest {
    @Test(description = "TS-006 TC-001 - Verify exact store address match")
    public void testVerifyExactStoreAddressMatch() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        helper.enterLocationAndSearch("Boston, MA");
        Assert.assertTrue(helper.verifyStoreAddressInResults("375 Washington Street, Boston, MA 02108"), "Exact store address should match");
    }
}
