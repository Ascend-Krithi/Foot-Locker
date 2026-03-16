package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC003 extends BaseTest {
    
    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-003")
    public void testSearchForStoresInBoston() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.dismissCookieConsent();
        homePage.closeModalIfPresent();
        
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        
        storeLocator.enterLocation("Boston, MA");
        
        storeLocator.clickSearchStores();
        
        boolean areResultsDisplayed = storeLocator.areStoreResultsDisplayed();
        Assert.assertTrue(areResultsDisplayed, "Store results for Boston and nearby locations should be listed");
    }
}