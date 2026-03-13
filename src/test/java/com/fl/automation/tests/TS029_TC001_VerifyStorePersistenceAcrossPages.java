package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS029_TC001_VerifyStorePersistenceAcrossPages extends BaseTest {

    @Test(description = "TC_4175: SCRUM-17166 TS-006 TC-001 - Verify store persistence across pages")
    public void testVerifyStorePersistenceAcrossPages() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToFootLocker();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.searchForStores("Boston, MA");
        storeLocator.setPreferredStore("375 Washington");
        
        homePage.navigateToFootLocker();
        
        boolean isConfirmationDisplayed = homePage.isStoreConfirmationDisplayed();
        Assert.assertTrue(isConfirmationDisplayed, "Store should persist across pages");
    }
}