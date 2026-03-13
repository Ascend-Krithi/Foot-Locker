package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003 extends BaseTest {

    @Test(description = "TC-4202: Search for stores in Boston, MA and verify results")
    public void testSearchStoresInBoston() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage();
        
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        homePage.enterLocation("Boston, MA");
        
        homePage.clickSearchForStores();
        
        boolean areResultsDisplayed = storeHelper.areStoreResultsDisplayed();
        Assert.assertTrue(areResultsDisplayed, "Store results should be displayed for Boston, MA");
        
        int resultsCount = storeHelper.getStoreResultsCount();
        Assert.assertTrue(resultsCount > 0, "At least one store should be found in/near Boston, MA");
    }
}