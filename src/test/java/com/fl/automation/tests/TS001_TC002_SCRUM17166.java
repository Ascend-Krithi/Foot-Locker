package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TS001_TC002_SCRUM17166 extends BaseTest {
    
    @Test(description = "SCRUM-17166 TS-001 TC-002: Verify Store locator popup functionality")
    public void testStoreLocatorPopup() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeHelper = new StoreLocatorHelper(driver);
        
        homePage.acceptCookies();
        
        storeHelper.clickFindStore();
        
        Assert.assertTrue(storeHelper.isSelectMyStoreVisible(), "Store locator popup should be visible");
    }
}