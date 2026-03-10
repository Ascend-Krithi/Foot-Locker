package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS013_TC001_ extends BaseTest {
    @Test(description = "TS-013 TC-001 - Verify store persists across multiple pages")
    public void testStorePersistsAcrossMultiplePages() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        helper.enterLocationAndSearch("Boston, MA");
        helper.setMyStoreForAddress("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(helper.isStorePersistedAfterNavigation(), "Store should persist across multiple pages");
    }
}
