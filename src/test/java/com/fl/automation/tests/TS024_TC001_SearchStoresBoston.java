package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 4171
 * Test Case: Search Stores Boston
 * Description: Launch Foot Locker homepage, click 'Find a Store', open popup window, enter 'Boston, MA' in Location textbox, click 'Search for Stores', verify search results displayed in popup window.
 */
public class TS024_TC001_SearchStoresBoston extends BaseTest {

    @Test
    public void searchStoresBoston() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage("https://www.footlocker.com");
        homePage.acceptCookies();
        
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.enterLocation("Boston, MA");
        storeLocator.clickSearchButton();
        
        boolean areResultsDisplayed = storeLocator.areSearchResultsDisplayed();
        Assert.assertTrue(areResultsDisplayed, "Search results are not displayed in popup window");
    }
}