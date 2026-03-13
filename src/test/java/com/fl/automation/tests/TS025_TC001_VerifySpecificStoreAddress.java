package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 4172
 * Test Case: Verify Specific Store Address
 * Description: Launch Foot Locker homepage, open Store Locator popup, enter 'Boston, MA', click 'Search for Stores', verify search results displayed, verify store with address '375 Washington Street, Boston, MA 02108' visible in results.
 */
public class TS025_TC001_VerifySpecificStoreAddress extends BaseTest {

    @Test
    public void verifySpecificStoreAddress() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage("https://www.footlocker.com");
        homePage.acceptCookies();
        
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.enterLocation("Boston, MA");
        storeLocator.clickSearchButton();
        
        boolean areResultsDisplayed = storeLocator.areSearchResultsDisplayed();
        Assert.assertTrue(areResultsDisplayed, "Search results are not displayed");
        
        boolean isStoreAddressVisible = storeLocator.isStoreAddressVisible("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(isStoreAddressVisible, "Store with address '375 Washington Street, Boston, MA 02108' is not visible in results");
    }
}