package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC_ID: 4188
 * Test Case: Verify Preferred Store Persists Across Pages
 * Description: Set '375 Washington Street, Boston, MA 02108' as preferred store, navigate to another page on website, verify preferred store remains set, verify preferred store still displayed in header or location indicator.
 */
public class TS029_TC007_VerifyPreferredStorePersistsAcrossPages extends BaseTest {

    @Test
    public void verifyPreferredStorePersistsAcrossPages() {
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
        Assert.assertTrue(isPreferredStoreStillSet, "Preferred store does not remain set across pages");
    }
}