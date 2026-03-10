package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {
    @Test(description = "TS-001 TC-001 - Validate Find Store popup message and Select My Store link")
    public void testFindStorePopupAndSelectMyStore() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        Assert.assertTrue(helper.isFindStorePopupVisible(), "Find Store popup should be visible");
        Assert.assertTrue(helper.clickSelectMyStore(), "Select My Store link should be clickable");
    }
}
