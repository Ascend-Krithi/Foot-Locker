package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC001__02 extends BaseTest {
    @Test(description = "TS-002 TC-001 - Validate Location textbox and Search button")
    public void testValidateLocationTextboxAndSearchButton() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        Assert.assertTrue(helper.enterLocationAndSearch("Boston, MA"), "Location textbox and Search button should be present");
    }
}
