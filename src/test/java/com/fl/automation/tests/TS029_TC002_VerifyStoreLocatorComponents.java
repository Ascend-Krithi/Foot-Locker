package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 4183
 * Test Case: Verify Store Locator Components
 * Description: Launch Foot Locker homepage, click 'Find a Store', click 'Select My Store' link within popup, verify 'Location' textbox and 'Search for Stores' button visible.
 */
public class TS029_TC002_VerifyStoreLocatorComponents extends BaseTest {

    @Test
    public void verifyStoreLocatorComponents() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage("https://www.footlocker.com");
        homePage.acceptCookies();
        
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        boolean isLocationInputDisplayed = storeLocator.isLocationInputDisplayed();
        Assert.assertTrue(isLocationInputDisplayed, "'Location' textbox is not visible");
        
        boolean isSearchButtonDisplayed = storeLocator.isSearchButtonDisplayed();
        Assert.assertTrue(isSearchButtonDisplayed, "'Search for Stores' button is not visible");
    }
}