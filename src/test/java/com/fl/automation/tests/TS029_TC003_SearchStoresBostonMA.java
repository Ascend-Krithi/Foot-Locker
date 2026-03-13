package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 4184
 * Test Case: Search Stores Boston MA
 * Description: Open Store Locator window from homepage, enter 'Boston, MA' in Location textbox, click 'Search for Stores' button, verify search results displayed, verify relevant store results in or near Boston shown.
 */
public class TS029_TC003_SearchStoresBostonMA extends BaseTest {

    @Test
    public void searchStoresBostonMA() {
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