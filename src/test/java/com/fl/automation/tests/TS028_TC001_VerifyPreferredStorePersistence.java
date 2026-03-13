package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 4175
 * Test Case: Verify Preferred Store Persistence
 * Description: Launch Foot Locker homepage, open Store Locator popup, set '375 Washington Street, Boston, MA 02108' as preferred store, navigate to another page, verify selected store remains set as preferred store across website.
 */
public class TS028_TC001_VerifyPreferredStorePersistence extends BaseTest {

    @Test
    public void verifyPreferredStorePersistence() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage("https://www.footlocker.com");
        homePage.acceptCookies();
        
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.enterLocation("Boston, MA");
        storeLocator.clickSearchButton();
        
        storeLocator.clickSetMyStoreForAddress("375 Washington Street, Boston, MA 02108");
        
        driver.get("https://www.footlocker.com/category/mens-shoes.html");
        
        boolean isPreferredStoreStillSet = storeLocator.isPreferredStoreDisplayedInHeader();
        Assert.assertTrue(isPreferredStoreStillSet, "Selected store does not remain set as preferred store across website");
    }
}