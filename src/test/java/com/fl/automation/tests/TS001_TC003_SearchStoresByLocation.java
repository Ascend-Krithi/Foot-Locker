package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 3195
 * Test Case: Search Stores By Location
 * Description: Launch homepage, open Store Locator, enter 'Boston, MA', 
 * click Search, verify search results displayed.
 */
public class TS001_TC003_SearchStoresByLocation extends BaseTest {

    @Test
    public void searchStoresByLocation() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage("https://www.footlocker.com");
        homePage.acceptCookies();
        
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.enterLocation("Boston, MA");
        storeLocator.clickSearchButton();
        
        boolean areResultsDisplayed = storeLocator.areSearchResultsDisplayed();
        Assert.assertTrue(areResultsDisplayed, "Search results are not displayed");
    }
}