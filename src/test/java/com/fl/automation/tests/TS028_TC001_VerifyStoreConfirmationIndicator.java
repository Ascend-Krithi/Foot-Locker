package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS028_TC001_VerifyStoreConfirmationIndicator extends BaseTest {

    @Test(description = "TC_4174: SCRUM-17166 TS-005 TC-001 - Verify store confirmation indicator")
    public void testVerifyStoreConfirmationIndicator() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToFootLocker();
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.searchForStores("Boston, MA");
        storeLocator.setPreferredStore("375 Washington");
        
        boolean isConfirmationDisplayed = homePage.isStoreConfirmationDisplayed();
        Assert.assertTrue(isConfirmationDisplayed, "Store confirmation indicator should be displayed");
    }
}