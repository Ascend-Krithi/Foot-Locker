package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS004_TC001__02 extends BaseTest {
    @Test(description = "TS-004 TC-001 - Search invalid location Xyzabc and verify no results")
    public void testSearchInvalidLocationAndVerifyNoResults() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        Assert.assertTrue(helper.enterLocationAndSearch("Xyzabc"), "Should be able to enter invalid location");
        Assert.assertTrue(helper.isEmptyResultsMessageVisible(), "No results message should be visible for invalid location");
    }
}
