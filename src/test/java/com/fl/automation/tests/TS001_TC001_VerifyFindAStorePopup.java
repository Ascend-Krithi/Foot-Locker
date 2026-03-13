package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 3193
 * Test Case: Verify Find A Store Popup
 * Description: Launch https://www.footlocker.com, click Find a Store, 
 * verify popup with 'Choose a preferred store' message and 'Select My Store' link.
 */
public class TS001_TC001_VerifyFindAStorePopup extends BaseTest {

    @Test
    public void verifyFindAStorePopup() {
        HomePage homePage = new HomePage(driver);
        
        homePage.navigateToHomePage("https://www.footlocker.com");
        homePage.acceptCookies();
        
        homePage.clickFindAStore();
        
        boolean isPopupMessageDisplayed = homePage.isPopupMessageDisplayed();
        Assert.assertTrue(isPopupMessageDisplayed, "Popup message 'Choose a preferred store' is not displayed");
        
        boolean isSelectMyStoreLinkDisplayed = homePage.isSelectMyStoreLinkDisplayed();
        Assert.assertTrue(isSelectMyStoreLinkDisplayed, "'Select My Store' link is not displayed");
    }
}