package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003 extends BaseTest {
    
    @Test(description = "TC 4202: Search for stores in Boston MA and verify results")
    public void searchForStoresInBoston() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage();
        
        storeLocator.clickFindAStore();
        
        storeLocator.clickSelectMyStore();
        
        storeLocator.enterLocation("Boston, MA");
        
        storeLocator.clickSearchForStores();
        
        Assert.assertTrue(storeLocator.areStoreResultsDisplayed(), 
            "Boston store results should be displayed");
    }
}