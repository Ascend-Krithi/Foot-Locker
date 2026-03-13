package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_StoreLocatorElements extends BaseTest {

    @Test(description = "TC_3194: SCRUM-17166 TS-001 TC-002 - Launch, click Find a Store, click Select My Store, verify Location textbox and Search button")
    public void testStoreLocatorElements() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToFootLocker();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        boolean isLocationInputDisplayed = storeLocator.isLocationInputDisplayed();
        boolean isSearchButtonDisplayed = storeLocator.isSearchButtonDisplayed();
        
        Assert.assertTrue(isLocationInputDisplayed, "Location input should be displayed");
        Assert.assertTrue(isSearchButtonDisplayed, "Search button should be displayed");
    }
}