package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_SearchStoresBoston extends BaseTest {

    @Test(description = "TC4202 - TS-001 TC-003: Open Find a Store popup, enter Boston MA, click Search, verify store results")
    public void testSearchStoresBoston() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        homePage.acceptCookiesIfPresent();
        
        homePage.clickFindStore();
        
        homePage.clickSelectMyStore();
        
        storeLocatorHelper.enterLocation("Boston MA");
        
        storeLocatorHelper.clickSearchButton();
        
        Assert.assertTrue(storeLocatorHelper.areStoreResultsDisplayed(), "Store results should be displayed after search");
    }
}