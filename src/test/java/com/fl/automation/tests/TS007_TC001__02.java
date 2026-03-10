package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS007_TC001__02 extends BaseTest {
    @Test(description = "TS-007 TC-001 - Verify no typos in store address")
    public void testNoTyposInStoreAddress() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        helper.enterLocationAndSearch("Boston, MA");
        Assert.assertTrue(helper.verifyStoreAddressInResults("375 Washington Street, Boston, MA 02108"), "Store address should have no typos");
    }
}
