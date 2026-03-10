package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS009_TC001_ extends BaseTest {
    @Test(description = "TS-009 TC-001 - Verify no confirmation for non-existent store")
    public void testNoConfirmationForNonExistentStore() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        helper.enterLocationAndSearch("Xyzabc");
        Assert.assertFalse(helper.isConfirmationIndicatorVisible(), "No confirmation indicator should be visible for non-existent store");
    }
}
