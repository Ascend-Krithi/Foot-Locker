package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001__03 extends BaseTest {
    @Test(description = "TS-001 TC-001 - Validate Find Store popup and message")
    public void testFindStorePopupAndMessage() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        Assert.assertTrue(helper.isFindStorePopupVisible(), "Find Store popup and message should be visible");
    }
}
