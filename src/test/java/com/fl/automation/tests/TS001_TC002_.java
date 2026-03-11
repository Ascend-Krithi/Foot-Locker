package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_ extends BaseTest {

    @Test(priority = 1, description = "Test Case - SCRUM-17166 TS-001 TC-002: Verify Store Locator popup with Location textbox and Search button")
    public void testStoreLocatorPopupWithLocationAndSearchButton() {
        HomePage homePage = new HomePage(driver);
        
        homePage.navigateToHomePage("https://www.footlocker.com");
        Assert.assertTrue(homePage.isHomePageLoaded(), "Homepage should load successfully");
        
        homePage.clickFindStore();
        Assert.assertTrue(homePage.isFindStorePopupDisplayed(), "Popup should appear below 'Find a Store'");
        
        homePage.clickSelectMyStore();
        Assert.assertTrue(homePage.isStoreLocatorPopupOpen(), "Store locator popup window should open");
        
        Assert.assertTrue(homePage.isLocationTextboxPresent(), "Popup should contain 'Location' textbox and 'Search for Stores' button");
    }
}