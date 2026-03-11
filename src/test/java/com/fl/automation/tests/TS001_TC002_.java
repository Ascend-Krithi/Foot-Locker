package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_ extends BaseTest {

    @Test
    public void testStoreLocatorPopupWithLocationAndSearchButton() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        Assert.assertTrue(driver.getTitle().contains("Foot Locker") || driver.getCurrentUrl().contains("footlocker.com"),
            "Step 1: Homepage should load");

        homePage.clickFindStore();

        Assert.assertTrue(homePage.isStorePopupDisplayed(),
            "Step 2: Popup should appear below 'Find a Store'");

        homePage.clickSelectMyStore();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertTrue(storeLocatorHelper.isLocationTextboxVisible() && storeLocatorHelper.isSearchButtonVisible(),
            "Step 4: Popup should contain 'Location' textbox and 'Search for Stores' button");
    }
}
