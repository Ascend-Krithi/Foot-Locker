package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {
    @Test(description = "TS-001 TC-003 - Search for stores in Boston, MA")
    public void testSearchStoresBoston() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        Assert.assertTrue(helper.enterLocationAndSearch("Boston, MA"), "Should be able to search for stores in Boston, MA");
    }
}
