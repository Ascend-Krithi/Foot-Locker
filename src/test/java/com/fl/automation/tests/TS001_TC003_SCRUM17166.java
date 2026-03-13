package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_SCRUM17166 extends BaseTest {
    
    @Test(description = "SCRUM-17166 TS-001 TC-003: Search stores in Boston")
    public void testSearchStoresBoston() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);
        
        homePage.acceptCookies();
        
        storeHelper.clickFindStore();
        
        storeHelper.enterLocation("Boston, MA");
        
        storeHelper.clickSearchButton();
        
        Assert.assertTrue(storeHelper.areStoreResultsDisplayed(), "Store results should be displayed for Boston");
    }
}