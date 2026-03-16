package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC007 extends BaseTest {
    
    @Test(description = "Test Case - SCRUM-17166 TS-001 TC-007")
    public void testPreferredStorePersistsAcrossPages() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.dismissCookieConsent();
        homePage.closeModalIfPresent();
        
        homePage.clickFindStore();
        homePage.clickSelectMyStore();
        
        storeLocator.enterLocation("Boston, MA");
        storeLocator.clickSearchStores();
        
        storeLocator.clickSetMyStoreForAddress("375 Washington Street, Boston, MA 02108");
        
        driver.navigate().to("https://www.footlocker.com/sneakers");
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        boolean isStoreStillSet = storeLocator.isStoreSetAsPreferred("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(isStoreStillSet, "Selected store (375 Washington Street, Boston, MA 02108) should still be shown as preferred");
    }
}