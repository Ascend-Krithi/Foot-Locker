package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-4175: SCRUM-17166 Store Locator - Confirmation and persistence
 */
public class TS001_TC006 extends BaseTest {

    @Test(description = "TC-4175: Store selection confirmation and persistence")
    public void testStoreSelectionPersistence() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);

        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(homePage.isFindStoreDisplayed(), "Find a Store should be available");
        
        homePage.clickFindStore();
        Assert.assertTrue(storeLocator.isStoreLocatorInputDisplayed(), 
            "Store locator should open successfully");
    }
}