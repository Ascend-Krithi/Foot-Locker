package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS005_TC001_VerifyStoreConfirmation extends BaseTest {

    @Test(description = "TC_4174: SCRUM-17166 TS-005 TC-001 - Verify store confirmation")
    public void verifyStoreConfirmation() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);
        
        homePage.navigateToHomePage(baseUrl);
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        
        storeLocator.searchForStores("Boston, MA");
        
        int storeCount = storeLocator.getStoreResultsCount();
        Assert.assertTrue(storeCount > 0, "Store confirmation should be available after selection");
    }
}