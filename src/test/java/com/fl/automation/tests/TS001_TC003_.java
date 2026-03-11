package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.fl.automation.listeners.TestListener.class)
public class TS001_TC003_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-003")
    public void testSearchForStoresInBoston() {
        homePage.clickFindAStore();
        Assert.assertTrue(homePage.isFindAStorePopupDisplayed(), "Popup should appear");
        storeLocatorHelper.clickSelectMyStore();
        Assert.assertTrue(storeLocatorHelper.isStoreLocatorPopupOpen(), "Store locator popup window should open");
        storeLocatorHelper.enterLocationAndSearch("Boston, MA");
        storeLocatorHelper.clickSearchForStoresButton();
        Assert.assertTrue(storeLocatorHelper.areStoreResultsDisplayed(), "Search results should be displayed with relevant stores in or near Boston");
    }
}