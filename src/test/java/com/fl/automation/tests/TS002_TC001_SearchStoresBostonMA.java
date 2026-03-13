package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS002_TC001_SearchStoresBostonMA extends BaseTest {

    @Test(description = "TC_4171: SCRUM-17166 TS-002 TC-001 - Search stores Boston MA")
    public void verifySearchStoresBostonMA() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage(baseUrl);
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.searchForStores("Boston, MA");
        
        int storeCount = storeLocator.getStoreResultsCount();
        Assert.assertTrue(storeCount > 0, "Stores in Boston MA should be found");
    }
}