package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC008_ extends BaseTest {
    @Test(description = "TS-001 TC-008 - Verify store persists across navigation")
    public void testStorePersistsAcrossNavigation() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        helper.enterLocationAndSearch("Boston, MA");
        helper.setMyStoreForAddress("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(helper.isStorePersistedAfterNavigation(), "Store should persist across navigation");
    }
}
