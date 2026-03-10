package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS012_TC001_ extends BaseTest {
    @Test(description = "TS-012 TC-001 - Verify store persists from homepage to product page")
    public void testStorePersistsFromHomepageToProductPage() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        helper.enterLocationAndSearch("Boston, MA");
        helper.setMyStoreForAddress("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(helper.isStorePersistedAfterNavigation(), "Store should persist from homepage to product page");
    }
}
