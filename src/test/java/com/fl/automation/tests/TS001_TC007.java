package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC007 extends BaseTest {
    
    @Test(description = "TC 4206: Verify preferred store persists across pages")
    public void verifyPreferredStorePersists() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage();
        
        storeLocator.clickFindAStore();
        
        storeLocator.clickSelectMyStore();
        
        storeLocator.enterLocation("Boston, MA");
        
        storeLocator.clickSearchForStores();
        
        storeLocator.setStoreAsPreferred("375 Washington Street");
        
        homePage.navigateToSneakersPage();
        
        Assert.assertTrue(homePage.getCurrentUrl().contains("sneakers"), 
            "Should navigate to sneakers page");
        
        boolean storeStillShown = storeLocator.isPreferredStoreShownInHeader();
        
        Assert.assertTrue(storeStillShown, 
            "Selected store should still be shown as preferred on sneakers page");
    }
}