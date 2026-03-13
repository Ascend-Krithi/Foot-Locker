package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 4170
 * Test Case: Verify Find A Store Flow
 * Description: Launch Foot Locker homepage, click 'Find a Store', verify popup appears with message, verify 'Select My Store' link visible, click 'Select My Store', verify 'Find a Store' popup window opens with 'Location' textbox and 'Search for Stores' button.
 */
public class TS023_TC001_VerifyFindAStoreFlow extends BaseTest {

    @Test
    public void verifyFindAStoreFlow() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage("https://www.footlocker.com/");
        homePage.acceptCookies();
        
        homePage.clickFindAStore();
        
        boolean isPopupMessageDisplayed = homePage.isPopupMessageDisplayed();
        Assert.assertTrue(isPopupMessageDisplayed, "Popup message 'Choose a preferred store to make shopping easier' is not displayed");
        
        boolean isSelectMyStoreLinkVisible = homePage.isSelectMyStoreLinkDisplayed();
        Assert.assertTrue(isSelectMyStoreLinkVisible, "'Select My Store' link is not visible");
        
        homePage.clickSelectMyStore();
        
        boolean isLocationInputDisplayed = storeLocator.isLocationInputDisplayed();
        Assert.assertTrue(isLocationInputDisplayed, "Location textbox is not displayed");
        
        boolean isSearchButtonDisplayed = storeLocator.isSearchButtonDisplayed();
        Assert.assertTrue(isSearchButtonDisplayed, "Search for Stores button is not displayed");
    }
}