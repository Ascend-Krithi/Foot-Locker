package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001 extends BaseTest {
    
    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-001")
    public void testFindAStorePopupDisplays() {
        HomePage homePage = new HomePage(driver);
        
        homePage.dismissCookieConsent();
        homePage.closeModalIfPresent();
        
        homePage.clickFindStore();
        
        boolean isPopupDisplayed = homePage.isStorePopupDisplayed();
        Assert.assertTrue(isPopupDisplayed, "Popup should appear below 'Find a Store' displaying the message 'Choose a preferred store to make shopping easier'");
        
        boolean isSelectMyStoreLinkVisible = homePage.isSelectMyStoreLinkVisible();
        Assert.assertTrue(isSelectMyStoreLinkVisible, "'Select My Store' link should be present and visible");
    }
}