package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_ extends BaseTest {

    @Test
    public void testStoreLocatorPopupElements() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.navigateTo("https://www.footlocker.com");
        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");

        homePage.clickFindStore();
        Assert.assertTrue(homePage.isPopupMessageDisplayed(), "Popup did not appear below 'Find a Store'");

        homePage.clickSelectMyStore();

        Assert.assertTrue(storeLocatorHelper.isLocationTextboxPresent(), "'Location' textbox is not present in the store locator popup");
        Assert.assertTrue(storeLocatorHelper.isSearchButtonPresent(), "'Search for Stores' button is not present in the store locator popup");
    }
}