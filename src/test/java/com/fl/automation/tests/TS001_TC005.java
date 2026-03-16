package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.annotations.Test;

public class TS001_TC005 extends BaseTest {
    
    @Test(description = "TC 4204: Set 375 Washington Street Boston as preferred store")
    public void setPreferredStore() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage();
        
        storeLocator.clickFindAStore();
        
        storeLocator.clickSelectMyStore();
        
        storeLocator.enterLocation("Boston, MA");
        
        storeLocator.clickSearchForStores();
        
        storeLocator.setStoreAsPreferred("375 Washington Street");
    }
}