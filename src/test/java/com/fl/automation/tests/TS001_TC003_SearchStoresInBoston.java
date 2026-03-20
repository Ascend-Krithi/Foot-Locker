package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_SearchStoresInBoston extends BaseTest {

    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-003: Search for stores in Boston, MA")
    public void testSearchStoresInBoston() {
        homePage.clickFindStoreButton();
        homePage.clickSelectMyStoreLink();
        
        Assert.assertTrue(homePage.isStoreLocatorModalDisplayed(), 
            "Find a Store popup window should be displayed");
        
        homePage.enterLocation("Boston, MA");
        
        homePage.clickSearchButton();
        
        Assert.assertTrue(homePage.areStoreResultsDisplayed(), 
            "Search results should be displayed in the popup window");
        
        Assert.assertTrue(homePage.areStoreResultsDisplayed(), 
            "Store results for Boston and nearby locations should be listed");
    }
}