package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_ extends BaseTest {

    @Test
    public void testCase_SCRUM17166_TS001_TC002() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);

        homePage.clickFindStore();
        Assert.assertTrue(homePage.isStorePopupDisplayed(), "Popup should appear below 'Find a Store'");

        homePage.clickSelectMyStore();

        Assert.assertTrue(storeHelper.isLocationTextboxVisible(), "Popup should contain 'Location' textbox");
        Assert.assertTrue(storeHelper.isSearchButtonVisible(), "Popup should contain 'Search for Stores' button");
    }
}