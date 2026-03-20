package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003_SearchStoresBoston extends BaseTest {
    
    @Test(description="Test Case - SCRUM-17166 TS-001 TC-003: Open Find a Store popup, enter Boston MA, click Search for Stores, verify results")
    public void testSearchStoresBoston() {
        homePage.acceptCookies();
        
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        
        storeLocatorHelper.waitForStoreLocatorToLoad();
        
        Assert.assertTrue(storeLocatorHelper.isStoreLocatorModalVisible(), "Find a Store popup window should be displayed");
        
        storeLocatorHelper.enterLocation("Boston, MA");
        
        storeLocatorHelper.clickSearch();
        
        storeLocatorHelper.waitForStoreResults();
        
        Assert.assertTrue(storeLocatorHelper.isStoreInResults("Boston"), "Store results for Boston and nearby locations should be listed");
    }
}