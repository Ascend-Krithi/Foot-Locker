package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS004_TC001_ extends BaseTest {
    @Test(description = "TS-004 TC-001 - Verify exact store address in results")
    public void testVerifyExactStoreAddress() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        helper.enterLocationAndSearch("Boston, MA");
        Assert.assertTrue(helper.verifyStoreAddressInResults("375 Washington Street, Boston, MA 02108"), "Exact store address should be present");
    }
}
