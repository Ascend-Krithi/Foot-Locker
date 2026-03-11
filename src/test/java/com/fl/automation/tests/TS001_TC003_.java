package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_ extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-003")
    public void testStoreSearchWithLocation() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage("https://www.footlocker.com");
        Assert.assertTrue(driver.getTitle().contains("Foot Locker"), "Homepage did not load correctly");
        
        homePage.clickFindStore();
        Assert.assertTrue(homePage.isSelectMyStoreLinkVisible(), "Popup did not appear");
        
        homePage.clickSelectMyStore();
        Assert.assertTrue(storeLocatorHelper.isSearchInputVisible(), "Store locator popup window did not open");
        
        storeLocatorHelper.enterLocation("Boston, MA");
        
        storeLocatorHelper.clickSearchButton();
        
        Assert.assertTrue(storeLocatorHelper.areStoreResultsDisplayed(), "Search results are not displayed with relevant stores in or near Boston");
    }
}