package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {

    @Test
    public void testStoreSearchForBostonMA() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);

        homePage.navigateTo("https://www.footlocker.com");
        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");

        homePage.clickFindStore();
        Assert.assertTrue(homePage.isPopupMessageDisplayed(), "Popup did not appear");

        homePage.clickSelectMyStore();

        storeLocatorHelper.enterLocation("Boston, MA");

        storeLocatorHelper.clickSearchButton();

        Assert.assertTrue(storeLocatorHelper.areStoreResultsDisplayed(), "Search results are not displayed with relevant stores in or near Boston");
    }
}