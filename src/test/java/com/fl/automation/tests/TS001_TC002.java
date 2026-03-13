package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002 extends BaseTest {

    @Test(description = "TC-4201: Verify store locator popup contains Location textbox and Search button")
    public void testStoreLocatorPopupElements() {
        HomePage homePage = new HomePage(driver);
        
        homePage.navigateToHomePage();
        
        homePage.clickFindAStore();
        
        homePage.clickSelectMyStore();
        
        boolean isLocationInputVisible = homePage.isLocationInputVisible();
        Assert.assertTrue(isLocationInputVisible, "Location textbox should be visible in the popup");
        
        boolean isSearchButtonVisible = homePage.isSearchButtonVisible();
        Assert.assertTrue(isSearchButtonVisible, "Search for Stores button should be visible in the popup");
    }
}