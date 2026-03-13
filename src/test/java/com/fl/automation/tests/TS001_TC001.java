package com.fl.automation.tests;

import com.fl.automation.core.BaseTest;
import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestRail TC-3193: SCRUM-17166 TS-001 TC-001
 * Test: Find Store popup validation
 */
public class TS001_TC001 extends BaseTest {

    @Test(description = "TC-3193: Find Store popup validation")
    public void testFindStorePopupValidation() {
        HomePage homePage = new HomePage(driver);
        StoreLocatorHelper storeLocator = new StoreLocatorHelper(driver);

        homePage.open("https://www.footlocker.com");
        homePage.acceptCookies();
        
        Assert.assertTrue(homePage.isFindStoreDisplayed(), "Find a Store link should be displayed");
        
        homePage.clickFindStore();
        
        Assert.assertTrue(storeLocator.isStoreLocatorInputDisplayed(), 
            "Store locator input should be displayed after clicking Find a Store");
    }
}