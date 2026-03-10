package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC001_ extends BaseTest {
    @Test(description = "TS-002 TC-001 - Click Select My Store and verify popup window")
    public void testClickSelectMyStoreAndVerifyPopup() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        Assert.assertTrue(helper.clickSelectMyStore(), "Select My Store should open popup window");
    }
}
