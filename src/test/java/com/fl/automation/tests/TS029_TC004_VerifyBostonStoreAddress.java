package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 4185
 * Test Case: Verify Boston Store Address
 * Description: Search for stores using 'Boston, MA' in Store Locator window, locate store with address '375 Washington Street, Boston, MA 02108' in results, verify store with exact address visible.
 */
public class TS029_TC004_VerifyBostonStoreAddress extends BaseTest {

    @Test
    public void verifyBostonStoreAddress() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage("https://www.footlocker.com");
        homePage.acceptCookies();
        
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.enterLocation("Boston, MA");
        storeLocator.clickSearchButton();
        
        boolean isStoreAddressVisible = storeLocator.isStoreAddressVisible("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(isStoreAddressVisible, "Store with exact address '375 Washington Street, Boston, MA 02108' is not visible");
    }
}