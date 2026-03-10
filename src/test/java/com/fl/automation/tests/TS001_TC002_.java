package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_ extends BaseTest {
    @Test(description = "TS-001 TC-002 - Validate Location textbox and Search for Stores button")
    public void testLocationTextboxAndSearchButton() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        Assert.assertTrue(helper.enterLocationAndSearch("Boston, MA"), "Location textbox and Search button should be present");
    }
}
