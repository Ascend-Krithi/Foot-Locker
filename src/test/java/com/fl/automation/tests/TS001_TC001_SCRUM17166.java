package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_SCRUM17166 extends BaseTest {

    @Test
    public void testCase_SCRUM17166_TS001_TC001() {
        HomePage homePage = new HomePage(driver);
        
        homePage.dismissCookieConsent();
        
        Assert.assertTrue(driver.getCurrentUrl().contains("footlocker.com"), "Sneakers, Apparel & More | Foot Locker homepage did not load");
        
        homePage.clickFindStore();
        
        Assert.assertTrue(homePage.isStorePopupDisplayed(), "A popup did not appear below 'Find a Store' displaying the message 'Choose a preferred store to make shopping easier'");
        
        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), "'Select My Store' link is not visible within the popup");
    }
}