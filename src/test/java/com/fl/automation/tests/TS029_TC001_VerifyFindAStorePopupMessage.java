package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 4182
 * Test Case: Verify Find A Store Popup Message
 * Description: Launch Foot Locker homepage, locate and click 'Find a Store' button/link in header, observe popup for message and link, verify popup displays correct message and link.
 */
public class TS029_TC001_VerifyFindAStorePopupMessage extends BaseTest {

    @Test
    public void verifyFindAStorePopupMessage() {
        HomePage homePage = new HomePage(driver);
        
        homePage.navigateToHomePage("https://www.footlocker.com");
        homePage.acceptCookies();
        
        homePage.clickFindAStore();
        
        boolean isPopupMessageDisplayed = homePage.isPopupMessageDisplayed();
        Assert.assertTrue(isPopupMessageDisplayed, "Popup does not display message 'Choose a preferred store to make shopping easier'");
        
        boolean isSelectMyStoreLinkDisplayed = homePage.isSelectMyStoreLinkDisplayed();
        Assert.assertTrue(isSelectMyStoreLinkDisplayed, "'Select My Store' link is not displayed in popup");
    }
}