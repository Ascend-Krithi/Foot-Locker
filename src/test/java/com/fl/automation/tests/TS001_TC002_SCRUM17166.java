package com.fl.automation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;

public class TS001_TC002_SCRUM17166 extends BaseTest {
    @Test
    public void testStoreLocatorPopup() {
        driver.get("https://www.footlocker.com");
        HomePage homePage = new HomePage(driver);
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        Assert.assertTrue(storeLocatorHelper.isLocationTextboxVisible(), "Location textbox should be visible");
        Assert.assertTrue(storeLocatorHelper.isSearchButtonVisible(), "Search for Stores button should be visible");
    }
}