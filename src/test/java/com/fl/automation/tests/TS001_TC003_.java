package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {

    @Test
    public void testCase_SCRUM17166_TS001_TC003() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);

        homePage.clickFindStore();
        Assert.assertTrue(homePage.isStorePopupDisplayed(), "Popup should appear");

        homePage.clickSelectMyStore();

        storeHelper.enterLocation("Boston, MA");

        storeHelper.clickSearchButton();

        Assert.assertTrue(storeHelper.areStoreResultsDisplayed(), "Search results should be displayed with relevant stores in or near Boston");
    }
}