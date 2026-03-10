package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC005_ extends BaseTest {
    @Test(description = "TS-001 TC-005 - Set My Store for Boston location")
    public void testSetMyStoreBoston() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        helper.enterLocationAndSearch("Boston, MA");
        Assert.assertTrue(helper.setMyStoreForAddress("375 Washington Street, Boston, MA 02108"), "Should be able to set My Store for Boston");
    }
}
