package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 4173
 * Test Case: Set Preferred Store
 * Description: Launch Foot Locker homepage, open Store Locator popup, search for stores using 'Boston, MA', click 'Set My Store' for store at '375 Washington Street, Boston, MA 02108', verify selected store saved as preferred store.
 */
public class TS026_TC001_SetPreferredStore extends BaseTest {

    @Test
    public void setPreferredStore() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage("https://www.footlocker.com");
        homePage.acceptCookies();
        
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.enterLocation("Boston, MA");
        storeLocator.clickSearchButton();
        
        storeLocator.clickSetMyStoreForAddress("375 Washington Street, Boston, MA 02108");
        
        boolean isPreferredStoreSet = storeLocator.isPreferredStoreSet();
        Assert.assertTrue(isPreferredStoreSet, "Selected store not saved as preferred store");
    }
}