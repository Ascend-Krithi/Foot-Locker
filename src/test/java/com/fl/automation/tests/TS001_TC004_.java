package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004_ extends BaseTest {
    @Test(description = "TS-001 TC-004 - Verify specific store address 375 Washington Street, Boston, MA 02108")
    public void testVerifyStoreAddress() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        helper.enterLocationAndSearch("Boston, MA");
        Assert.assertTrue(helper.verifyStoreAddressInResults("375 Washington Street, Boston, MA 02108"), "Store address should be present in results");
    }
}
