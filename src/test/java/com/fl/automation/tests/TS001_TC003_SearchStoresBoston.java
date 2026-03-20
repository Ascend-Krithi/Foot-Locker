package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_SearchStoresBoston extends BaseTest {

    @Test(description = "TC4202 - Search for stores in Boston MA")
    public void testSearchStoresBoston() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocatorHelper = new StoreLocatorHelper(driver);
        
        homePage.acceptCookiesIfPresent();
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        storeLocatorHelper.waitForStoreLocatorToLoad();
        
        storeLocatorHelper.enterLocation("Boston MA");
        storeLocatorHelper.clickSearchButton();
        storeLocatorHelper.waitForStoreResults();
        
        Assert.assertTrue(storeLocatorHelper.isStoreDisplayed("375 Washington Street"), 
            "Store results for Boston should be displayed");
    }
}