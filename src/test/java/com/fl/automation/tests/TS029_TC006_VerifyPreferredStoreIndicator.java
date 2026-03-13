package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 4187
 * Test Case: Verify Preferred Store Indicator
 * Description: Set '375 Washington Street, Boston, MA 02108' as preferred store, observe confirmation indicator after selection, navigate to another page, verify preferred store appears consistently.
 */
public class TS029_TC006_VerifyPreferredStoreIndicator extends BaseTest {

    @Test
    public void verifyPreferredStoreIndicator() {
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
        Assert.assertTrue(isPreferredStoreSet, "Confirmation indicator not displayed after selection");
        
        driver.get("https://www.footlocker.com/category/mens-shoes.html");
        
        boolean isPreferredStoreInHeader = storeLocator.isPreferredStoreDisplayedInHeader();
        Assert.assertTrue(isPreferredStoreInHeader, "Preferred store does not appear consistently in header or location indicator");
    }
}