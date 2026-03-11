package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {

    @Test(priority = 1, description = "Test Case - SCRUM-17166 TS-001 TC-003: Verify store search functionality with Boston, MA location")
    public void testStoreSearchWithBostonLocation() {
        HomePage homePage = new HomePage(driver);
        
        homePage.navigateToHomePage("https://www.footlocker.com");
        Assert.assertTrue(homePage.isHomePageLoaded(), "Homepage should load successfully");
        
        homePage.clickFindStore();
        Assert.assertTrue(homePage.isFindStorePopupDisplayed(), "Popup should appear");
        
        homePage.clickSelectMyStore();
        Assert.assertTrue(homePage.isStoreLocatorPopupOpen(), "Store locator popup window should open");
        
        homePage.enterLocation("Boston, MA");
        
        Assert.assertTrue(homePage.isSearchResultsDisplayed(), "Search results should be displayed with relevant stores in or near Boston");
    }
}