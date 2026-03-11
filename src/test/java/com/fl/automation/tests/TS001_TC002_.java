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
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        homePage.clickFindStore();
        Assert.assertTrue(homePage.isStorePopupDisplayed(), "Popup appears below 'Find a Store'.");
        
        homePage.clickSelectMyStore();
        
        Assert.assertTrue(storeLocatorHelper.isLocationTextboxVisible(), "Popup contains 'Location' textbox and 'Search for Stores' button.");
    }
}