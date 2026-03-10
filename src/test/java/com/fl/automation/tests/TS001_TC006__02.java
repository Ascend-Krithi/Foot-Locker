package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006__02 extends BaseTest {
    @Test(description = "TS-001 TC-006 - Set My Store for Boston")
    public void testSetMyStoreForBoston() {
        StoreLocatorHelper helper = new StoreLocatorHelper(driver);
        helper.openHomePage();
        helper.enterLocationAndSearch("Boston, MA");
        Assert.assertTrue(helper.setMyStoreForAddress("375 Washington Street, Boston, MA 02108"), "Should set My Store for Boston");
    }
}
