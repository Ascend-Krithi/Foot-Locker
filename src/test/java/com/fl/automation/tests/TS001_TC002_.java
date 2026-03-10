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

        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");

        homePage.clickFindAStore();

        Assert.assertTrue(homePage.isPopupDisplayed(), "Popup did not appear below 'Find a Store'");

        storeLocatorHelper.clickSelectMyStore();

        Assert.assertTrue(storeLocatorHelper.isLocationTextboxPresent(), "'Location' textbox is not present in the popup");

        Assert.assertTrue(storeLocatorHelper.isSearchForStoresButtonPresent(), "'Search for Stores' button is not present in the popup");
    }
}