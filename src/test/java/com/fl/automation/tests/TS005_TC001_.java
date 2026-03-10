package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS005_TC001_ extends BaseTest {
    @Test(description = "TS-005 TC-001 - Set My Store and verify saved")
    public void testSetMyStoreAndVerifySaved() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        helper.enterLocationAndSearch("Boston, MA");
        Assert.assertTrue(helper.setMyStoreForAddress("375 Washington Street, Boston, MA 02108"), "Should set My Store and verify saved");
    }
}
