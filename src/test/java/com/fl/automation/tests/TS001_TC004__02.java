package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC004__02 extends BaseTest {
    @Test(description = "TS-001 TC-004 - Search Boston and verify results")
    public void testSearchBostonAndVerifyResults() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        Assert.assertTrue(helper.enterLocationAndSearch("Boston, MA"), "Should be able to search Boston and verify results");
    }
}
