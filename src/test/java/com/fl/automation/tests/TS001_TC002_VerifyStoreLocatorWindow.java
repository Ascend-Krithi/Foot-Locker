package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 3194
 * Test Case: Verify Store Locator Window
 * Description: Launch homepage, click Find a Store, click Select My Store, 
 * verify Location textbox and Search for Stores button.
 */
public class TS001_TC002_VerifyStoreLocatorWindow extends BaseTest {

    @Test
    public void verifyStoreLocatorWindow() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage("https://www.footlocker.com");
        homePage.acceptCookies();
        
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        boolean isLocationInputDisplayed = storeLocator.isLocationInputDisplayed();
        Assert.assertTrue(isLocationInputDisplayed, "Location textbox is not displayed");
        
        boolean isSearchButtonDisplayed = storeLocator.isSearchButtonDisplayed();
        Assert.assertTrue(isSearchButtonDisplayed, "Search for Stores button is not displayed");
    }
}