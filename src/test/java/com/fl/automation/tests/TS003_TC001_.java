package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS003_TC001_ extends BaseTest {
    @Test(description = "TS-003 TC-001 - Search Boston MA and verify results")
    public void testSearchBostonAndVerifyResults() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        Assert.assertTrue(helper.enterLocationAndSearch("Boston, MA"), "Should be able to search Boston, MA");
    }
}
