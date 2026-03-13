package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS024_TC001_FootLockerFindAStoreFullFlow extends BaseTest {

    @Test(description = "TC_4170: SCRUM-17166 TS-001 TC-001 - Foot Locker: Find a Store full flow")
    public void testFootLockerFindAStoreFullFlow() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToFootLocker();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.searchForStores("Boston, MA");
        
        boolean areResultsDisplayed = storeLocator.areStoreResultsDisplayed();
        Assert.assertTrue(areResultsDisplayed, "Store results should be displayed");
    }
}