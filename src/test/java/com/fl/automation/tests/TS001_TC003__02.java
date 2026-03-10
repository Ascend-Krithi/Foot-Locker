package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003__02 extends BaseTest {
    @Test(description = "TS-001 TC-003 - Verify Location textbox and Search button")
    public void testVerifyLocationTextboxAndSearchButton() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        Assert.assertTrue(helper.enterLocationAndSearch("Boston, MA"), "Location textbox and Search button should be present");
    }
}
