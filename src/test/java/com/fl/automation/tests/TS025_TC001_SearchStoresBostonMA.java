package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS025_TC001_SearchStoresBostonMA extends BaseTest {

    @Test(description = "TC_4171: SCRUM-17166 TS-002 TC-001 - Search for stores in Boston MA")
    public void testSearchStoresBostonMA() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToFootLocker();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.searchForStores("Boston, MA");
        
        boolean areResultsDisplayed = storeLocator.areStoreResultsDisplayed();
        Assert.assertTrue(areResultsDisplayed, "Store results should be displayed for Boston, MA");
    }
}