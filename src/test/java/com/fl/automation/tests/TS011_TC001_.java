package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS011_TC001_ extends BaseTest {
    @Test(description = "TS-011 TC-001 - Verify store appears consistently across pages")
    public void testStoreAppearsConsistentlyAcrossPages() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        helper.enterLocationAndSearch("Boston, MA");
        helper.setMyStoreForAddress("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(helper.isStorePersistedAfterNavigation(), "Store should appear consistently across pages");
    }
}
