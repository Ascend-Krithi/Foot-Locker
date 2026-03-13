package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_SearchStoresBoston extends BaseTest {

    @Test(description = "TC_3195: SCRUM-17166 TS-001 TC-003 - Search for stores in Boston, MA")
    public void searchStoresInBoston() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage(baseUrl);
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.searchForStores("Boston, MA");
        
        int storeCount = storeLocator.getStoreResultsCount();
        Assert.assertTrue(storeCount > 0, "Store results should be displayed for Boston, MA");
        Assert.assertTrue(storeLocator.verifyStoreAddressContains("Boston"), "Store addresses should contain Boston");
    }
}