package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC001 extends BaseTest {
    
    @Test(description = "TC 4200: Verify Find a Store popup displays with Select My Store link")
    public void verifyFindAStorePopupDisplays() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage();
        
        storeLocator.clickFindAStore();
        
        Assert.assertTrue(storeLocator.isFindAStorePopupDisplayed(), 
            "Find a Store popup should be displayed");
        
        Assert.assertTrue(storeLocator.isSelectMyStoreLinkPresent(), 
            "Select My Store link should be present in the popup");
    }
}