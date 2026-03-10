package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS005_TC001__02 extends BaseTest {
    @Test(description = "TS-005 TC-001 - Search with empty location and verify validation")
    public void testSearchWithEmptyLocationAndVerifyValidation() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        Assert.assertTrue(helper.enterLocationAndSearch(""), "Should be able to submit empty location");
        Assert.assertTrue(helper.isEmptyResultsMessageVisible(), "Validation message should be visible for empty location");
    }
}
