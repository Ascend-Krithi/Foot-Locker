package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 4174
 * Test Case: Verify Preferred Store Confirmation
 * Description: Launch Foot Locker homepage, open Store Locator popup, set '375 Washington Street, Boston, MA 02108' as preferred store, verify confirmation indicator displayed, verify selected store appears consistently across website.
 */
public class TS027_TC001_VerifyPreferredStoreConfirmation extends BaseTest {

    @Test
    public void verifyPreferredStoreConfirmation() {
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
        Assert.assertTrue(isPreferredStoreSet, "Preferred store confirmation indicator not displayed");
        
        boolean isPreferredStoreInHeader = storeLocator.isPreferredStoreDisplayedInHeader();
        Assert.assertTrue(isPreferredStoreInHeader, "Selected store does not appear consistently across website");
    }
}