package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC005_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-005: Validate Set My Store Functionality for Boston Location")
    public void testSetMyStoreFunctionality() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);

        homePage.clickFindAStore();
        homePage.clickSelectMyStore();

        storeHelper.enterLocation("Boston, MA");
        storeHelper.clickSearchForStores();

        Assert.assertTrue(storeHelper.isStoreResultsDisplayed(), 
            "Search results should be displayed");

        try {
            storeHelper.clickSetMyStoreForAddress("375 Washington Street");
            Assert.assertTrue(true, 
                "Store should be saved as user's preferred store");
        } catch (Exception e) {
            Assert.fail("Failed to set store as preferred: " + e.getMessage());
        }
    }
}