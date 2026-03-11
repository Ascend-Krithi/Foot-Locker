package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.fl.automation.listeners.TestListener.class)
public class TS001_TC002_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-002")
    public void testStoreLocatorPopupContainsLocationTextboxAndSearchButton() {
        homePage.clickFindAStore();
        Assert.assertTrue(homePage.isFindAStorePopupDisplayed(), "Popup should appear below 'Find a Store'");
        storeLocatorHelper.clickSelectMyStore();
        Assert.assertTrue(storeLocatorHelper.isStoreLocatorPopupOpen(), "Store locator popup window should open");
        Assert.assertTrue(storeLocatorHelper.isLocationTextboxPresent(), "Popup should contain 'Location' textbox");
        Assert.assertTrue(storeLocatorHelper.isSearchForStoresButtonPresent(), "Popup should contain 'Search for Stores' button");
    }
}