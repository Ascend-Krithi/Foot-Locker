package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS006_TC001_ extends BaseTest {
    @Test(description = "TS-006 TC-001 - Verify confirmation indicator and consistency")
    public void testConfirmationIndicatorAndConsistency() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        helper.enterLocationAndSearch("Boston, MA");
        helper.setMyStoreForAddress("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(helper.isConfirmationIndicatorVisible(), "Confirmation indicator should be visible and consistent");
    }
}
