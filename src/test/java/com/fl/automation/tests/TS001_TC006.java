package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC006 extends BaseTest {
    
    @Test(description = "TC 4205: Verify confirmation after setting preferred store")
    public void verifyPreferredStoreConfirmation() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage();
        
        storeLocator.clickFindAStore();
        
        storeLocator.clickSelectMyStore();
        
        storeLocator.enterLocation("Boston, MA");
        
        storeLocator.clickSearchForStores();
        
        storeLocator.setStoreAsPreferred("375 Washington Street");
        
        boolean confirmationDisplayed = storeLocator.isPreferredStoreConfirmationDisplayed() || 
                                       storeLocator.isPreferredStoreShownInHeader();
        
        Assert.assertTrue(confirmationDisplayed, 
            "Confirmation indicator or store in header should be displayed after setting preferred store");
    }
}