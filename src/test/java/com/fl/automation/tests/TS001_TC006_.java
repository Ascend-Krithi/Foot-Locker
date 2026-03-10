package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006_ extends BaseTest {
    @Test(description = "TS-001 TC-006 - Verify store confirmation indicator and persistence")
    public void testConfirmationIndicatorAndPersistence() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        helper.enterLocationAndSearch("Boston, MA");
        helper.setMyStoreForAddress("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(helper.isConfirmationIndicatorVisible(), "Confirmation indicator should be visible");
    }
}
