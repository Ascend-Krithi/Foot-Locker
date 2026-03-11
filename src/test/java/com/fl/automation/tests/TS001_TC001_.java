package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001_ extends BaseTest {

    @Test(priority = 1, description = "Test Case - SCRUM-17166 TS-001 TC-001: Verify Find a Store popup and Select My Store link")
    public void testFindStorePopupAndSelectMyStoreLink() {
        HomePage homePage = new HomePage(driver);
        
        homePage.navigateToHomePage("https://www.footlocker.com");
        Assert.assertTrue(homePage.isHomePageLoaded(), "Homepage should load successfully");
        
        homePage.clickFindStore();
        Assert.assertTrue(homePage.isFindStorePopupDisplayed(), "Popup should appear below 'Find a Store' displaying the message 'Choose a preferred store to make shopping easier'");
        
        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), "'Select My Store' link should be visible within the popup");
    }
}