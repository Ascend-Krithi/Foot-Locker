package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 4186
 * Test Case: Set My Store Boston
 * Description: Search for stores using 'Boston, MA', locate '375 Washington Street, Boston, MA 02108', click 'Set My Store' for Boston location, verify store saved as preferred store.
 */
public class TS029_TC005_SetMyStoreBoston extends BaseTest {

    @Test
    public void setMyStoreBoston() {
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
        Assert.assertTrue(isPreferredStoreSet, "Store not saved as preferred store");
    }
}