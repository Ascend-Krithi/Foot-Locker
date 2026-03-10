package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-003: Validate Store Search Functionality with Boston MA")
    public void testStoreSearchFunctionality() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);

        homePage.clickFindAStore();
        homePage.clickSelectMyStore();

        storeHelper.enterLocation("Boston, MA");
        storeHelper.clickSearchForStores();

        Assert.assertTrue(storeHelper.isStoreResultsDisplayed(), 
            "Search results should be displayed with relevant stores in or near Boston");
    }
}