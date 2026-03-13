package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_SearchStoresBoston extends BaseTest {

    @Test(description = "TC_3195: SCRUM-17166 TS-001 TC-003 - Launch, open store locator, enter Boston MA, click Search, verify results")
    public void testSearchStoresBoston() {
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